package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.statistics.StatsTrendCommand;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.BudgetPeriod;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.Timestamp;

/**
 * Represents the Statistics class that provides a trend line as its Visual Representation method
 */
public class TrendStatistics extends Statistics {

    public static final int INTERVAL_COUNT = 34;

    private Timestamp startDate;

    private Timestamp endDate;

    private Budget primaryBudget;

    private boolean budgetLimitMode;

    private ObservableList<Expense> expenses;

    private List<Timestamp> dates = new ArrayList<>();

    private List<Double> periodicTotalExpenditure = new ArrayList<>();

    private List<Double> periodicBudgetLimits = new ArrayList<>();

    private List<ArrayList<Double>> periodicCategoricalExpenses = new ArrayList<>();

    private TrendStatistics(ObservableList<Expense> expenses, List<Category> validCategories,
                            Timestamp startDate, Timestamp endDate, Budget primaryBudget, boolean isBudgetMode) {

        super(expenses, validCategories);
        this.startDate = startDate;
        this.endDate = endDate;
        this.expenses = getExpenses();
        this.primaryBudget = primaryBudget;
        this.budgetLimitMode = isBudgetMode;
        if (!budgetLimitMode) {
            for (int i = 0; i < validCategories.size(); i++) {
                periodicCategoricalExpenses.add(new ArrayList<>());
            }
        }
    }

    /**
     * Creates 2 trend lines to provide visual aid on the occurrence of total expenses relative to the budget limit
     *
     * @param expenses List of expenses
     * @param validCategories List of allowed categories in MooLah
     * @param startDate The start date of the tracking period
     * @param endDate The end date of the tracking period
     * @param primaryBudget The primary budget whose statistics is taken
     */
    public static TrendStatistics run(ObservableList<Expense> expenses, List<Category> validCategories,
                                      Timestamp startDate, Timestamp endDate,
                                      Budget primaryBudget, boolean isBudgetMode) {

        requireNonNull(primaryBudget);
        boolean isStartPresent = startDate != null;
        boolean isEndPresent = endDate != null;

        if (isStartPresent && isEndPresent) {
            //pass
        } else if (isStartPresent) {
            endDate = startDate.createForwardTimestamp(primaryBudget.getPeriod(),
                    2 * StatsTrendCommand.HALF_OF_PERIOD_NUMBER);
        } else if (isEndPresent) {
            startDate = endDate.createBackwardTimestamp(primaryBudget.getPeriod(),
                    2 * StatsTrendCommand.HALF_OF_PERIOD_NUMBER);
        } else {
            Timestamp centreDate = primaryBudget.getStartDate();
            endDate = centreDate.createForwardTimestamp(primaryBudget.getPeriod(),
                    StatsTrendCommand.HALF_OF_PERIOD_NUMBER);
            startDate = centreDate.createBackwardTimestamp(primaryBudget.getPeriod(),
                    StatsTrendCommand.HALF_OF_PERIOD_NUMBER);
        }

        TrendStatistics statistics = TrendStatistics.verify(expenses, validCategories,
                startDate, endDate, primaryBudget, isBudgetMode);

        statistics.generateTrendLine();
        return statistics;

    }

    /**
     * A method to practise defensive programming
     * @param expenses List of expenses
     * @param validCategories List of allowed categories in MooLah
     * @param startDate The start date of the tracking period
     * @param endDate The end date of the tracking period
     */

    /**
     * A method to practise defensive programming
     * @param expenses List of expenses
     * @param validCategories List of allowed categories in MooLah
     * @param startDate The start date of the tracking period
     * @param endDate The end date of the tracking period
     * @param primaryBudget The primary budget whose statistics is taken
     * @param isBudgetMode The condition to determine which mode is used
     */
    private static TrendStatistics verify(ObservableList<Expense> expenses, List<Category> validCategories,
                                             Timestamp startDate, Timestamp endDate,
                                          Budget primaryBudget, boolean isBudgetMode) {
        requireNonNull(startDate);
        requireNonNull(endDate);
        requireNonNull(expenses);
        requireNonNull(validCategories);

        return new TrendStatistics(expenses, validCategories,
                startDate, endDate, primaryBudget, isBudgetMode);
    }

    /**
     * Gathers the data to be used for the elements of the trend line
     */
    private void generateTrendLine() {
        ArrayList<ArrayList<ArrayList<Expense>>> data = new ArrayList<>();


        BudgetPeriod period = primaryBudget.getPeriod();
        Timestamp windowStartDate = primaryBudget.getStartDate();

        Timestamp validDate = findClosestWindowStartDate(startDate, windowStartDate, period);

        int intervalCount = 0;

        while (hasInterval(validDate, endDate, period) && intervalCount < INTERVAL_COUNT) {
            Timestamp localStartDate = validDate;

            Timestamp nextLocalStartDate = localStartDate.plus(period.getPeriod());
            Timestamp localEndDate = nextLocalStartDate.minusDays(1);

            ArrayList<ArrayList<Expense>> periodicCategorisedExpenses =
                    getPeriodicCategorisedExpenses(localStartDate, localEndDate);

            if (budgetLimitMode) {
                double periodicTotalExpenditure = getTotalExpenditure(periodicCategorisedExpenses);
                this.periodicTotalExpenditure.add(periodicTotalExpenditure);
                periodicBudgetLimits.add(primaryBudget.getAmount().getAsDouble());
            } else {
                List<Double> periodicCategoricalExpenditure = getCategoricalExpenditure(periodicCategorisedExpenses);
                flatMapAdd(periodicCategoricalExpenditure);
            }
            dates.add(localStartDate);
            intervalCount++;
            validDate = nextLocalStartDate;

        }

        this.setTitle(String.format("Periodic trendline from %s to %s in the unit of %ss",
                startDate.showDate(), endDate.showDate(),
                period));
    }

    /**
     * Finds the window start date that is closest to the start date of interest
     * @param startDate The start date for the trend line
     * @param windowStartDate The start date for the current winndow
     * @param period The duration of the window
     * @return
     */
    private Timestamp findClosestWindowStartDate(Timestamp startDate, Timestamp windowStartDate, BudgetPeriod period) {
        Timestamp changedWindowDate = windowStartDate.createBackwardTimestamp(period);
        while (windowStartDate.dateIsAfter(startDate) && changedWindowDate.dateIsAfter(startDate)) {
            windowStartDate = changedWindowDate;
            changedWindowDate = changedWindowDate.createBackwardTimestamp(period);
        }

        return windowStartDate;
    }

    /**
     * Adds the periodic expenditure from each category to its respective category list
     * @param periodicCategoricalExpenditure A list of total expenditure for each category
     */
    private void flatMapAdd(List<Double> periodicCategoricalExpenditure) {
        for (int i = 0; i < periodicCategoricalExpenditure.size(); i++) {
            ArrayList<Double> categoricalExpenses = periodicCategoricalExpenses.get(i);
            categoricalExpenses.add(periodicCategoricalExpenditure.get(i));
        }
    }

    private ArrayList<ArrayList<Expense>> getPeriodicCategorisedExpenses(Timestamp startDate, Timestamp endDate) {
        TabularStatistics statistics = new TabularStatistics(expenses, getValidCategories(), startDate, endDate);
        ArrayList<ArrayList<Expense>> dataWithTotal = statistics.extractRelevantExpenses(startDate, endDate);
        dataWithTotal.remove(dataWithTotal.size() - 1);
        return dataWithTotal;
    }

    private double getExpenditureForCategory(ArrayList<Expense> categorisedExpenses) {
        double total = 0;
        for (Expense expense : categorisedExpenses) {
            total += Double.parseDouble(expense.getPrice().value);
        }
        return total;
    }

    private double getTotalExpenditure(ArrayList<ArrayList<Expense>> data) {
        double total = 0;
        for (ArrayList<Expense> categorisedExpenses : data) {
            total += getExpenditureForCategory(categorisedExpenses);
        }
        return total;
    }

    private List<Double> getCategoricalExpenditure(ArrayList<ArrayList<Expense>> data) {
        ArrayList<Double> result = new ArrayList<>();
        for (ArrayList<Expense> categorisedExpenses : data) {
            result.add(getExpenditureForCategory(categorisedExpenses));
        }
        return result;
    }

    public List<ArrayList<Double>> getPeriodicCategoricalExpenses() {
        return periodicCategoricalExpenses;
    }

    private static boolean hasInterval (Timestamp validDate, Timestamp endDate, BudgetPeriod period) {
        return validDate.isBefore(endDate) && (validDate.plus(period.getPeriod())).isBefore(endDate);
    }

    public List<Timestamp> getDates() {
        return dates;
    }

    public List<Double> getPeriodicTotalExpenditure() {
        return periodicTotalExpenditure;
    }

    public List<Double> getPeriodicBudgetLimits() {
        return periodicBudgetLimits;
    }

    public boolean isBudgetLimitMode() {
        return budgetLimitMode;
    }

    public String toString () {
        return String.format("%s\n%s", getTitle(), getPeriodicTotalExpenditure());
    }
}



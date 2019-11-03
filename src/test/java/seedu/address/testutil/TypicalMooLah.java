package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_BUFFET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION_BUFFET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_PRICE_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_PRICE_BUFFET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TIMESTAMP_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TIMESTAMP_BUFFET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_CATEGORY_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_CATEGORY_TAXI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DESCRIPTION_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DESCRIPTION_TAXI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_PRICE_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_PRICE_TAXI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MooLah;
import seedu.address.model.budget.Budget;
import seedu.address.model.expense.Event;
import seedu.address.model.expense.Expense;

/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalMooLah {

    public static final String SCHOOL_BUDGET_STRING_ONE = "|| Description: School related expenses "
            + "Amount: 300 Period: month Start date: 15 Nov 2019, 12:00:00 PM End date: 14 Dec 2019, 12:00:00 PM ||";

    public static final String SCHOOL_BUDGET_STRING_TWO = "|| Description: School related expenses "
            + "Amount: 300 Period: month Start date: Nov 15, 2019, 12:00:00 PM End date: Dec 14, 2019, 12:00:00 PM ||";

    public static final Budget SCHOOL = new BudgetBuilder()
            .withDescription("School related expenses")
            .withAmount("300")
            .withStartDate("15-11-2019 noon")
            .withPeriod("month")
            .withEndDate("14-12-2019 noon")
            .withIsPrimary(true)
            .build();

    public static final Budget OUTSIDE_SCHOOL = new BudgetBuilder()
            .withDescription("Outside school expenses")
            .withAmount("200")
            .withStartDate("05-09-2019 noon")
            .withPeriod("month")
            .withEndDate("04-10-2019 noon")
            .withIsPrimary(false)
            .build();

    public static final Expense ANNIVERSARY = new ExpenseBuilder()
            .withDescription("Alices Birthday")
            .withPrice("20")
            .withCategory("Entertainment")
            .withTimestamp("01-12-2019 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000001")
            .build();
    public static final Expense BUSAN_TRIP = new ExpenseBuilder()
            .withDescription("Busan Trip")
            .withPrice("1300")
            .withCategory("Transport")
            .withTimestamp("02-12 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000002")
            .build();
    public static final Expense CHICKEN_RICE = new ExpenseBuilder()
            .withDescription("Chicken Rice extra Chicken")
            .withPrice("3.50")
            .withCategory("Food")
            .withTimestamp("03-12-2019 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000003")
            .build();
    public static final Expense DRINKS = new ExpenseBuilder()
            .withDescription("Whiskey and Coke")
            .withPrice("50")
            .withCategory("Food")
            .withTimestamp("04-12 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000004")
            .build();
    public static final Expense ENTERTAINMENT = new ExpenseBuilder()
            .withDescription("Marvel Movie Marathon")
            .withPrice("75")
            .withCategory("Entertainment")
            .withTimestamp("05-12 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000005")
            .build();
    public static final Expense FASHION = new ExpenseBuilder()
            .withDescription("Clothes for the New Year")
            .withPrice("88.88")
            .withCategory("Shopping")
            .withTimestamp("06-12 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000006")
            .build();
    public static final Expense GROCERIES = new ExpenseBuilder()
            .withDescription("Groceries for September meal preps")
            .withPrice("125.35")
            .withCategory("Utilities")
            .withTimestamp("07-12 noon")
            .withBudgetName("Default Budget")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000007")
            .build();

    // Manually added
    public static final Expense HALLOWEEN = new ExpenseBuilder()
            .withDescription("HalloweenHorrorNight")
            .withPrice("60")
            .withCategory("Entertainment")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000008").build();
    public static final Expense INVESTMENT = new ExpenseBuilder()
            .withDescription("Property investment")
            .withPrice("1200000")
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-000000000009").build();

    // Manually added - Expense's details found in {@code CommandTestUtil}
    public static final Expense CHICKEN = new ExpenseBuilder()
            .withDescription(VALID_EXPENSE_DESCRIPTION_CHICKEN)
            .withPrice(VALID_EXPENSE_PRICE_CHICKEN)
            .withCategory(VALID_EXPENSE_CATEGORY_TAXI)
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-00000000000a").build();
    public static final Expense TRANSPORT = new ExpenseBuilder()
            .withDescription(VALID_EXPENSE_DESCRIPTION_TAXI)
            .withPrice(VALID_EXPENSE_PRICE_TAXI)
            .withCategory(VALID_EXPENSE_CATEGORY_CHICKEN)
            .withUniqueIdentifier("Expense@00000000-0000-0000-0000-00000000000b").build();

    public static final Event BRIAN_BDAY = new EventBuilder()
            .withDescription("Brian bday")
            .withPrice("30.5")
            .withCategory("Shopping")
            .withTimestamp("01-01-2020 noon")
            .withBudgetName("Default Budget")
            .build();
    public static final Event BANGKOK_TRIP = new EventBuilder()
            .withDescription("Bangkok trip")
            .withPrice("500")
            .withCategory("Transport")
            .withTimestamp("02-01-2020 noon")
            .withBudgetName("Default Budget")
            .build();
    public static final Event GIRLFRIEND_ANNIVERSARY = new EventBuilder()
            .withDescription("Girlfriend anniversary")
            .withPrice("50")
            .withCategory("Shopping")
            .withTimestamp("03-01-2020 noon")
            .withBudgetName("Default Budget")
            .build();
    public static final Event DRINKS_WITH_FRIENDS = new EventBuilder()
            .withDescription("Drinks with friends")
            .withPrice("80")
            .withCategory("Food")
            .withTimestamp("04-01-2020 noon")
            .withBudgetName("Default Budget")
            .build();
    public static final Event USS_TRIP = new EventBuilder()
            .withDescription("USS trip")
            .withPrice("50")
            .withCategory("Entertainment")
            .withTimestamp("07-01-2020 noon")
            .withBudgetName("Default Budget")
            .build();

    //    // Manually added
    //    public static final Event HALLOWEEN = new EventBuilder()
    //            .withDescription("HalloweenHorrorNight")
    //            .withPrice("60")
    //            .withCategory("Entertainment")
    //            .build();
    //    public static final Event INVESTMENT = new EventBuilder()
    //            .withDescription("Property investment")
    //            .withPrice("1200000")
    //            .build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event BUFFET = new EventBuilder()
            .withDescription(VALID_EVENT_DESCRIPTION_BUFFET)
            .withPrice(VALID_EVENT_PRICE_BUFFET)
            .withTimestamp(VALID_EVENT_TIMESTAMP_BUFFET)
            .withCategory(VALID_EVENT_CATEGORY_BUFFET)
            .build();
    public static final Event BIRTHDAY = new EventBuilder()
            .withDescription(VALID_EVENT_DESCRIPTION_BIRTHDAY)
            .withPrice(VALID_EVENT_PRICE_BIRTHDAY)
            .withTimestamp(VALID_EVENT_TIMESTAMP_BIRTHDAY)
            .withCategory(VALID_EVENT_CATEGORY_BIRTHDAY)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMooLah() {} // prevents instantiation

    /**
     * Returns an {@code MooLah} with all the typical expenses and events.
     */
    public static MooLah getTypicalMooLah() {
        MooLah ab = new MooLah();
        for (Budget budget : getTypicalBudgets()) {
            ab.addBudget(budget);
        }
        for (Expense expense : getTypicalExpenses()) {
            ab.addExpense(expense);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(
                ANNIVERSARY, BUSAN_TRIP, CHICKEN_RICE, DRINKS, ENTERTAINMENT, FASHION, GROCERIES));
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(
                BRIAN_BDAY, BANGKOK_TRIP, GIRLFRIEND_ANNIVERSARY, DRINKS_WITH_FRIENDS, USS_TRIP));
    }

    public static List<Budget> getTypicalBudgets() {
        return new ArrayList<>(Arrays.asList(
                SCHOOL, OUTSIDE_SCHOOL));
    }
}

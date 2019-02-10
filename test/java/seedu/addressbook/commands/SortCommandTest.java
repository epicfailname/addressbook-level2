package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;

import static seedu.addressbook.data.AddressBookTest.assertAddressBookEqual;

public class SortCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
    
    Command sortCommand;
    
    private Person aliceBetsy;
    private Person bobChaplin;
    private Person charlieDouglas;
    private Person davidElliot;


    private AddressBook sortedAddressBook;
    private AddressBook unsortedAddressBook;
    private AddressBook emptyAddressBook;
    
    @Before
    public void setUp() throws Exception {
        sortCommand = new SortCommand();
        
        aliceBetsy     = new Person(new Name("Alice Betsy"),
                new Phone("91235468", false),
                new Email("alice@nushackers.org", false),
                new Address("8 Computing Drive, Singapore", false),
                Collections.emptySet());

        bobChaplin     = new Person(new Name("Bob Chaplin"),
                new Phone("94321500", false),
                new Email("bob@nusgreyhats.org", false),
                new Address("9 Computing Drive", false),
                Collections.emptySet());

        charlieDouglas = new Person(new Name("Charlie Douglas"),
                new Phone("98751365", false),
                new Email("charlie@nusgdg.org", false),
                new Address("10 Science Drive", false),
                Collections.emptySet());

        davidElliot    = new Person(new Name("David Elliot"),
                new Phone("84512575", false),
                new Email("douglas@nuscomputing.com", false),
                new Address("11 Arts Link", false),
                Collections.emptySet());

        emptyAddressBook = new AddressBook();

        unsortedAddressBook = new AddressBook();
        unsortedAddressBook.addPerson(charlieDouglas);
        unsortedAddressBook.addPerson(aliceBetsy);
        unsortedAddressBook.addPerson(davidElliot);

        sortedAddressBook = new AddressBook();
        sortedAddressBook.addPerson(aliceBetsy);
        sortedAddressBook.addPerson(charlieDouglas);
        sortedAddressBook.addPerson(davidElliot);
    }
    
    @Test
    public void execute_sortEmptyAddressBook_emptyAddressBook() {
        AddressBook emptyAddressBookClone = TestUtil.clone(emptyAddressBook);
        assertSortSuccess(sortCommand, emptyAddressBookClone, emptyAddressBook);
    }

    @Test
    public void execute_sortUnorderedAddressBook_sortsNormally() {
        assertSortSuccess(sortCommand, unsortedAddressBook, sortedAddressBook);
    }

    @Test
    public void execute_sortOrderedAddressBook_doesNothing() {
        AddressBook sortedAddressBookClone = TestUtil.clone(sortedAddressBook);
        assertSortSuccess(sortCommand, sortedAddressBookClone, sortedAddressBook);
    }


    /**
     * Executes the test command for the given addressbook data.
     * Checks that sortCommand succeeds.
     * 1. The feedback message of the CommandResult it returns matches SortCommand success message.
     * 2. The actualAddressBook is equal to the expectedAddressBook.
     */
    private static void assertSortSuccess(Command sortCommand,
                                         AddressBook actualAddressBook, AddressBook expectedAddressBook) {
        sortCommand.setData(actualAddressBook, EMPTY_PERSON_LIST);
        CommandResult result = sortCommand.execute();
        String actualMessage = result.feedbackToUser;
        String expectedMessage = SortCommand.MESSAGE_SORT_SUCCESS;

        // feedback message is as expected and there are no relevant persons returned.
        assertEquals(expectedMessage, actualMessage);
        assertEquals(Optional.empty(), result.getRelevantPersons());

        // addressbooks are equal
        assertAddressBookEqual(actualAddressBook, expectedAddressBook);
    }

}

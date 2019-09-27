package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniquePersonList mUniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(mUniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        mUniquePersonList.add(ALICE);
        assertTrue(mUniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        mUniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(mUniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        mUniquePersonList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> mUniquePersonList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> mUniquePersonList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        mUniquePersonList.add(ALICE);
        mUniquePersonList.setPerson(ALICE, ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        mUniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        mUniquePersonList.setPerson(ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        mUniquePersonList.add(ALICE);
        mUniquePersonList.setPerson(ALICE, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        mUniquePersonList.add(ALICE);
        mUniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> mUniquePersonList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> mUniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        mUniquePersonList.add(ALICE);
        mUniquePersonList.remove(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        mUniquePersonList.add(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        mUniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        mUniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        mUniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, mUniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePeople = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> mUniquePersonList.setPersons(listWithDuplicatePeople));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> mUniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}

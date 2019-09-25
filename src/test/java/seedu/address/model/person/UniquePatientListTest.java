package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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

public class UniquePatientListTest {

    private final UniquePatientList mUniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(mUniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        mUniquePatientList.add(ALICE);
        assertTrue(mUniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        mUniquePatientList.add(ALICE);
        Patient editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(mUniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        mUniquePatientList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> mUniquePatientList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> mUniquePatientList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        mUniquePatientList.add(ALICE);
        mUniquePatientList.setPerson(ALICE, ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(ALICE);
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        mUniquePatientList.add(ALICE);
        Patient editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        mUniquePatientList.setPerson(ALICE, editedAlice);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        mUniquePatientList.add(ALICE);
        mUniquePatientList.setPerson(ALICE, BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        mUniquePatientList.add(ALICE);
        mUniquePatientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> mUniquePatientList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> mUniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        mUniquePatientList.add(ALICE);
        mUniquePatientList.remove(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.setPersons((UniquePatientList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        mUniquePatientList.add(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        mUniquePatientList.setPersons(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mUniquePatientList.setPersons((List<Patient>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        mUniquePatientList.add(ALICE);
        List<Patient> patientList = Collections.singletonList(BOB);
        mUniquePatientList.setPersons(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, mUniquePatientList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> mUniquePatientList.setPersons(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> mUniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}

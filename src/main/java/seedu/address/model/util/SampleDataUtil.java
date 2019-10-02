package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Type;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Type("doctor"), new Nric("S1111111A"), new Name("Alex Yeoh"), new Phone("87438807")),
            new Person(new Type("doctor"), new Nric("T1333333P"), new Name("Bernice Yu"), new Phone("99272758")),
            new Person(new Type("doctor"), new Nric("G1234213L"), new Name("Charlotte Oliveiro"),
                    new Phone("93210283")),
            new Person(new Type("doctor"), new Nric("F5734625D"), new Name("David Li"), new Phone("91031282")),
            new Person(new Type("doctor"), new Nric("S6243536R"), new Name("Irfan Ibrahim"), new Phone("92492021")),
            new Person(new Type("doctor"), new Nric("T6746356G"), new Name("Roy Balakrishnan"), new Phone("92624417"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }


}

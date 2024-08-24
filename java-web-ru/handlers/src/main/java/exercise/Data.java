package exercise;

import net.datafaker.Faker;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public final class Data {
    private static final int ITEMS_COUNT = 10;
    private static final Random RANDOM = new Random(123);

    private Data() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getPhones() {
        Faker faker = new Faker(RANDOM);
        List<String> phones = new ArrayList<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            phones.add(faker.phoneNumber().cellPhone());
        }

        return phones;
    }

    public static List<String> getDomains() {
        Faker faker = new Faker(RANDOM);
        List<String> domains = new ArrayList<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            domains.add(faker.internet().domainName());
        }

        return domains;
    }
}

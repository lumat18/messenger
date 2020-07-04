package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UsernameService {

    private final String[] NAMES = {"kot", "pies", "chomik", "koń", "krowa", "kura", "indyk", "kogut", "świnia", "słoń", "żyrafa", "tygrys", "lew", "mrówka", "pająk", "mysz polna", "jaszczurka", "żmija", "wąż", "zając", "dzik", "królik", "wiewiórka", "żaba", "sarna", "jeleń", "pantera", "kuna", "małpa", "goryl", "hipopotam", "niedźwiedź", "krokodyl", "borsuk", "wilk", "lis", "fretka", "surykatka", "panda", "jeż", "jeżozwierz", "hiena", "leniwiec", "zebra", "kameleon", "łoś", "lama", "jastrząb", "jaskółka,sowa", "wrona", "gawron", "bocian biały", "bocian czarny", "jemiołuszka", "dzięcioł", "kawka", "wróbel", "sikorka bogatka", "papuga", "wieloryb", "delfin", "mors", "żółw", "bóbr", "foka", "czapla", "pingwin"};
    private final Random RANDOM = new Random();
    private final List<String> availableUserNames = new LinkedList<>();
    private int lastGenerated = 0;

    public String generateNewUsername() {
        if (availableUserNames.size() == 0) {
            lastGenerated++;
            availableUserNames.addAll(Arrays.stream(NAMES)
                    .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1) + (lastGenerated == 1 ? "" : (" " + lastGenerated)))
                    .collect(Collectors.toList()));
        }
        final int randomIndex = RANDOM.nextInt(availableUserNames.size());
        return availableUserNames.remove(randomIndex);
    }
}

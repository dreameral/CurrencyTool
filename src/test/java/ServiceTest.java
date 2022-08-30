import com.currencytool.model.Currency;
import com.currencytool.model.DataRow;
import com.currencytool.repository.impl.CurrencyRepositoryImpl;
import com.currencytool.repository.impl.CurrencyToolRepositoryImpl;
import com.currencytool.service.CurrencyToolService;
import com.currencytool.service.impl.CurrencyToolServiceImpl;
import com.currencytool.util.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ServiceTest {

    private CurrencyToolService service;
    private File dataFile;

    @Before
    public void setUp() {
        service = new CurrencyToolServiceImpl(new CurrencyToolRepositoryImpl(), new CurrencyRepositoryImpl());
        ClassLoader classLoader = getClass().getClassLoader();
        dataFile = new File(classLoader.getResource("testdata.csv").getFile());
    }

    @Test
    public void testShowAll() throws Exception {
        service.loadDataFromFile(dataFile);
        Optional<DataRow> optionalRow = service.getReferencesByDate(Utils.parseDate("2022-08-25"));
        Optional<DataRow> emptyOptional = service.getReferencesByDate(Utils.parseDate("2022-08-29"));

        assertTrue(emptyOptional.isEmpty());
        assertTrue(optionalRow.isPresent());
        DataRow row = optionalRow.get();

        assertEquals(0.997, row.getCurrencyValue("USD").get());
        assertEquals(136.07, row.getCurrencyValue("JPY").get());
        assertTrue(row.getCurrencyValue("CYP").isEmpty());

    }

    @Test
    public void testConvertCurrency() throws Exception {
        service.loadDataFromFile(dataFile);

        Double convertedValue = service.convertCurrency(Utils.parseDate("2022-08-25"), new Currency("USD"), new Currency("GBP"), 5d);
        assertEquals(4.227331995987964, convertedValue);
    }

    @Test
    public void testHighestReference() throws Exception {
        service.loadDataFromFile(dataFile);

        Double highest = service.findHighestReference(Utils.parseDate("2022-08-20"), Utils.parseDate("2022-08-25"), new Currency("USD"));
        assertEquals(1.0001, highest);

    }

    @Test
    public void testAverageReference() throws Exception {
        service.loadDataFromFile(dataFile);

        Double average = service.findAverageReference(Utils.parseDate("2022-08-20"), Utils.parseDate("2022-08-25"), new Currency("USD"));
        assertEquals(0.9954000000000001, average);

    }

}

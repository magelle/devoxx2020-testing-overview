package contract;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import devoxxfr2020.DiscountApplication;
import devoxxfr2020.cashregister.domain.AppliedBasketDiscount;
import devoxxfr2020.cashregister.domain.CashRegister;
import devoxxfr2020.cashregister.domain.Receipt;
import devoxxfr2020.cashregister.domain.ReceiptItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = DiscountApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port=8080"
)
@Provider("receipt-service")
@PactBroker(
        scheme = "https",
        host = "magelle.pact.dius.com.au",
        port = "443",
        authentication = @PactBrokerAuth(token = "ZOhnNi5ai-J1jXja2P7ytg")
)
public class ReceiptServiceContractTest {

    @MockBean
    private CashRegister cashRegister;

    private static String PROVIDER_URL = "http://localhost:8080";

    @BeforeEach
    public void beforeEach() {

    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    public void before(PactVerificationContext context) throws MalformedURLException {
        when(cashRegister.editReceipt(anyList())).thenReturn(
                new Receipt(
                        List.of(new ReceiptItem("Pommes", 3, 300)),
                        List.of(new AppliedBasketDiscount("More than 5 apples", 100)),
                        500)
        );

        context.setTarget(HttpTestTarget.fromUrl(new URL(PROVIDER_URL)));
    }
}

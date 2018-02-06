package bc;

import io.nem.apps.builders.ConfigurationBuilder;
import io.nem.apps.builders.TransferTransactionBuilder;
import io.nem.apps.fee.TransactionFeeCalculatorAfterForkForApp;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import java.util.concurrent.ExecutionException;

public class NEMTest {

    protected String TEST_DM_ADDRESS = "TDBL7EZIG5BKO5YFFTKWSYKKLOJUAAADS2PC572D";


    /**
     * The sample msg.
     */
    final String sampleMsg = "{\n" +
            "      \"deviceId\": \"DEVICE-ID\",\n" +
            "      \"command\": \"SensorData\",\n" +
            "      \"data\": \"[\\\"xxx\\\"]\",\n" +
            "      \"createDate\": 1476711243326,\n" +
            "      \"nodeId\": \"NODE-ID\",\n" +
            "      \"sensorId\": \"SENSOR-ID\",\n" +
            "      \"cloudDate\": 1476711243354\n" +
            "    }";


    Account senderPrivateAccount;
    Account recipientPublicAccount;

    @Before
    public void setup() throws ExecutionException, InterruptedException {


        String networkName = "testnet";
        ConfigurationBuilder.nodeNetworkName(networkName).nodeNetworkProtocol("http")
                .nodeNetworkUri("104.128.226.60").nodeNetworkPort("7890")
                .transactionFee(new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), null))
                .setup();

//        AccountMetaDataPair accountMetaDataPair = AccountApi.getAccountByAddress(TEST_DM_ADDRESS);
//        System.out.println(accountMetaDataPair.getEntity().getBalance());

        this.senderPrivateAccount = new Account(new KeyPair(PrivateKey
                .fromHexString("ed8984ba76793c74b702c9b6ab2a7ee468a50c8c608574d2debff60d1f7d5e05")));
        this.recipientPublicAccount = new Account(new KeyPair(
                PublicKey.fromHexString("ce38a3dd2e772a8ab6ea93fddc5019c77a3152bef27ff72b619ecf5be8811b0d")));
//        this.recipientPublicAccount = new Account(Address.fromEncoded("TDBL7E-ZIG5BK-O5YFFT-KWSYKK-LOJUAA-ADS2PC-572D"));
    }

    @Test
    public void testBasicTransferTans() {
//        NemAnnounceResult result = TransferTransactionBuilder
//                .sender()
//                .recipient()
//                .message()
//                .fee(Amount.fromMicroNem(50_000L)).buildAndSendTransaction();


        final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
                this.recipientPublicAccount, sampleMsg.getBytes());

        NemAnnounceResult result = TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
                //.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
                .message(sampleMsg, MessageTypes.PLAIN)
//                .fee(Amount.fromMicroNem(500_000L))
                .feeCalculator(new TransactionFeeCalculatorAfterForkForApp()) // custom fee calculator
                .amount(Amount.ZERO)
                .buildAndSendTransaction();
        System.out.println(result.getCode());
        System.out.println(result.getTransactionHash());
        System.out.println(result.getMessage());
        System.out.println(result.getTransactionHash().getRaw());

    }
}

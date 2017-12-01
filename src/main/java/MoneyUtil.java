import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

/**
 * @author yuton
 * @version 1.0
 * @description PACKAGE_NAME
 * @since 下午5:25 2017/11/30
 */
public class MoneyUtil {

    public static void main(String[] args) {

        //单位创建
        CurrencyUnit usd = Monetary.getCurrency("USD");
        System.out.println(usd);
        System.out.println(usd.getCurrencyCode());
        System.out.println(usd.getNumericCode());
        System.out.println(usd.getDefaultFractionDigits());

        //金额表示
        MonetaryAmount fstAmtUSD = Monetary
                .getDefaultAmountFactory()
                .setCurrency(usd)
                .setNumber(200)
                .create();
        Money money = Money.of(12, usd);
        FastMoney fastMoney = FastMoney.of(2, usd);
        System.out.println(fstAmtUSD.toString());
        System.out.println(money.toString());
        System.out.println(fastMoney.toString());

        //货币计算
        MonetaryAmount oneDolar = Monetary
                .getDefaultAmountFactory()
                .setCurrency(usd)
                .setNumber(1)
                .create();
        Money oneEuro = Money.of(1, "EUR");
        //结果一样但是对象不同
        System.out.println(oneEuro.equals(FastMoney.of(1, "EUR")));
        System.out.println(oneDolar.equals(Money.of(1, "USD")));

        //"+"
        MonetaryAmount[] monetaryAmounts = new MonetaryAmount[]{
                Money.of(100, "CHF"),
                Money.of(10.20, "CHF"),
                Money.of(1.15, "CHF")};
        Money sumAmtCHF = Money.of(0, "CHF");
        for (MonetaryAmount monetaryAmount : monetaryAmounts) {
            sumAmtCHF = sumAmtCHF.add(monetaryAmount);
        }
        System.out.println(sumAmtCHF.toString());

        //"-"
        Money calcAmtUSD = Money.of(1, "USD").subtract(fstAmtUSD);
        System.out.println(calcAmtUSD);

        //"*"
        MonetaryAmount multiplyAmount = oneDolar.multiply(0.25);
        System.out.println(multiplyAmount);

        //"\"
        MonetaryAmount divideAmount = oneDolar.divide(0.25);
        System.out.println(divideAmount);

        Money moneyOf = Money.of(12, usd);
        fstAmtUSD = Monetary.getDefaultAmountFactory()
                .setCurrency(usd).setNumber(200.50).create();
        oneDolar = Monetary.getDefaultAmountFactory()
                .setCurrency("USD").setNumber(1).create();
        Money subtractedAmount = Money.of(1, "USD").subtract(fstAmtUSD);
        multiplyAmount = oneDolar.multiply(0.25);
        divideAmount = oneDolar.divide(0.25);
        System.out.println("USD".equals(usd.toString()));
        System.out.println("USD 1".equals(oneDolar.toString()));
        System.out.println("USD 200.5".equals(fstAmtUSD.toString()));
        System.out.println("USD 12".equals(moneyOf.toString()));
        System.out.println("USD -199.5".equals(subtractedAmount.toString()));
        System.out.println("USD 0.25".equals(multiplyAmount.toString()));
        System.out.println("USD 4".equals(divideAmount.toString()));

        //四舍五入
        MonetaryAmount fstAmtEUR = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR").setNumber(1.30473908).create();
        MonetaryAmount roundEUR = fstAmtEUR.with(Monetary.getDefaultRounding());
        System.out.println("EUR 1.30473908".equals(fstAmtEUR.toString()));
        System.out.println("EUR 1.3".equals(roundEUR.toString()));

        MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory().setCurrency("USD")
                .setNumber(1).create();

        //货币格式
        MonetaryAmountFormat formatUSD = MonetaryFormats.getAmountFormat(Locale.US);
        String usFormatted = formatUSD.format(oneDollar);
        System.out.println("USD 1".equals(oneDollar.toString()));
        System.out.println(formatUSD);
        System.out.println("USD1.00".equals(usFormatted));
    }
}

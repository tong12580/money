import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

/**
 * @author yuton
 * @version 1.0
 * @description PACKAGE_NAME
 * @since 下午5:25 2017/11/30
 */
public class MoneyUtil {
    public static void main(String[] args) {
        CurrencyUnit usd = Monetary.getCurrency("USD");
        System.out.println(usd);
        System.out.println(usd.getCurrencyCode());
        System.out.println(usd.getNumericCode());
        System.out.println(usd.getDefaultFractionDigits());

        MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory().setCurrency(usd).setNumber(200).create();
        Money money = Money.of(12, usd);
        FastMoney fastMoney = FastMoney.of(2, usd);
        System.out.println(fstAmtUSD.toString());
        System.out.println(money.toString());
        System.out.println(fastMoney.toString());
    }
}

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitTestSistemPerpajakan {
    static SistemPerpajakan Pajak;

    @BeforeAll
    public static void init(){
        Pajak = new SistemPerpajakan();
    }
    @AfterAll
    static void destroy(){
        Pajak = null;
    }

    //NilaiEcYgdiTest
    private static Stream<Arguments> vECTestParameters() {
        return Stream.of(
                Arguments.of(0, 3800000),
                Arguments.of(10, 12000000),
                Arguments.of(22, 35000000),
                Arguments.of(40f, 98999999999f),
                Arguments.of(-1, -3000000),
                Arguments.of(-1, 10000000000000d)
        );
    }
    //Method uji EC berdasarkan class skenario
    @ParameterizedTest
    @MethodSource("vECTestParameters")
    public void sistemECPajakTest(double expected, double salary){
        assertNotNull(Pajak);
        assertEquals(expected, Pajak.getPajak(salary));
    }
    private static Stream<Arguments> BVAParameters(){
        return Stream.of(
                //BVA 1 & 2

                Arguments.of(true, 3999999),
                Arguments.of(true, 4000000),
                Arguments.of(false, 4000001)

                //BVA 2 & 3
                /*
                Arguments.of(true, 14999999),
                Arguments.of(true, 15000000),
                Arguments.of(false, 15000001)
                */
                //BVA 3 & 4
                /*
                Arguments.of(true, 39999999),
                Arguments.of(true, 40000000),
                Arguments.of(false, 40000001)
                 */
                //BVA 4
                /*
                Arguments.of(true, 999999999998f),
                Arguments.of(true, 999999999999f),
                Arguments.of(false, 1000000000000d)
                   */
        );
    }
    @ParameterizedTest
    @MethodSource("BVAParameters")
    public void BVASistemPajakTest(boolean expected, double salary){
        assertNotNull(Pajak);
        //BVA untuk EC 1 dan 2
        assertEquals(expected, Pajak.getPajak(salary)==0);
        //BVA untuk test 2 dan 3
        //assertEquals(expected, Pajak.getPajak(salary)==10);
        //BVA untuk test 3 dan 4
        //assertEquals(expected, Pajak.getPajak(salary)==22);
        //BVA untuk test 4
        //assertEquals(expected, Pajak.getPajak(salary)==40f);
    }


}

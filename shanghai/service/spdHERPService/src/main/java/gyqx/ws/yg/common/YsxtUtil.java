package gyqx.ws.yg.common;

import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.vo.repVo.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

/**
 * Created by moonb on 2017/11/21.
 */
@Component
public class YsxtUtil {
    @Resource
    private YgSendUtil ygUtils;
    public YY154_REP_XML getSendBill() throws Exception{
        Thread.sleep(5000);
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<XMLDATA>\n" +
                "    <HEAD>\n" +
                "        <JSSJ>20180312/094508/</JSSJ>\n" +
                "        <ZTCLJG>00000</ZTCLJG>\n" +
                "        <CWXX></CWXX>\n" +
                "        <BZXX></BZXX>\n" +
                "    </HEAD>\n" +
                "    <MAIN>\n" +
                "        <SFWJ>0</SFWJ>\n" +
                "        <DCZHPSDBH>20171103010000321212</DCZHPSDBH>\n" +
                "        <JLS>30</JLS>\n" +
                "    </MAIN>\n" +
                "    <DETAIL>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318529</PSDBH>\n" +
                "            <PSDH>SO201710-3635-1</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318551</PSDBH>\n" +
                "            <PSDH>SO201710-3635-2</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>6</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318576</PSDBH>\n" +
                "            <PSDH>SO201710-3635-3</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>2</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318589</PSDBH>\n" +
                "            <PSDH>SO201710-3635-4</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>2</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318615</PSDBH>\n" +
                "            <PSDH>SO201710-3635-5</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318638</PSDBH>\n" +
                "            <PSDH>SO201710-3635-6</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318661</PSDBH>\n" +
                "            <PSDH>SO201710-3635-7</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318674</PSDBH>\n" +
                "            <PSDH>SO201710-3635-8</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>2</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318720</PSDBH>\n" +
                "            <PSDH>SO201710-3635-9</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318737</PSDBH>\n" +
                "            <PSDH>SO201710-3635-10</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318757</PSDBH>\n" +
                "            <PSDH>SO201710-3635-11</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318764</PSDBH>\n" +
                "            <PSDH>2918-M1571</PSDH>\n" +
                "            <QYBM>QY000000000000000291</QYBM>\n" +
                "            <PSMXTS>9</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318770</PSDBH>\n" +
                "            <PSDH>SO201710-3635-12</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318783</PSDBH>\n" +
                "            <PSDH>SO201710-3635-13</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318816</PSDBH>\n" +
                "            <PSDH>SO201710-3635-14</PSDH>\n" +
                "            <QYBM>QY000000000000000346</QYBM>\n" +
                "            <PSMXTS>3</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000318993</PSDBH>\n" +
                "            <PSDH>P20171101</PSDH>\n" +
                "            <QYBM>QY000000000000002496</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000319283</PSDBH>\n" +
                "            <PSDH>2424</PSDH>\n" +
                "            <QYBM>QY000000000000000044</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000319316</PSDBH>\n" +
                "            <PSDH>2425</PSDH>\n" +
                "            <QYBM>QY000000000000000044</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000319341</PSDBH>\n" +
                "            <PSDH>2363</PSDH>\n" +
                "            <QYBM>QY000000000000000044</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171101010000319440</PSDBH>\n" +
                "            <PSDH>2469</PSDH>\n" +
                "            <QYBM>QY000000000000000044</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320270</PSDBH>\n" +
                "            <PSDH>HSYYBY</PSDH>\n" +
                "            <QYBM>QY000000000000003316</QYBM>\n" +
                "            <PSMXTS>4</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320457</PSDBH>\n" +
                "            <PSDH>YZJ2017110201</PSDH>\n" +
                "            <QYBM>QY000000000000001270</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320934</PSDBH>\n" +
                "            <PSDH>03561936</PSDH>\n" +
                "            <QYBM>QY000000000000001941</QYBM>\n" +
                "            <PSMXTS>22</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320936</PSDBH>\n" +
                "            <PSDH>03561937</PSDH>\n" +
                "            <QYBM>QY000000000000001941</QYBM>\n" +
                "            <PSMXTS>11</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320940</PSDBH>\n" +
                "            <PSDH>03561938</PSDH>\n" +
                "            <QYBM>QY000000000000001941</QYBM>\n" +
                "            <PSMXTS>6</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320941</PSDBH>\n" +
                "            <PSDH>03561939</PSDH>\n" +
                "            <QYBM>QY000000000000001941</QYBM>\n" +
                "            <PSMXTS>6</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171102010000320942</PSDBH>\n" +
                "            <PSDH>03561940</PSDH>\n" +
                "            <QYBM>QY000000000000001941</QYBM>\n" +
                "            <PSMXTS>2</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171103010000321080</PSDBH>\n" +
                "            <PSDH>20171102</PSDH>\n" +
                "            <QYBM>QY000000000000000326</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171103010000321095</PSDBH>\n" +
                "            <PSDH>20171103010600833297</PSDH>\n" +
                "            <QYBM>QY000000000000002433</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSDBH>20171103010000321212</PSDBH>\n" +
                "            <PSDH>NMD20171102A04</PSDH>\n" +
                "            <QYBM>QY000000000000001372</QYBM>\n" +
                "            <PSMXTS>1</PSMXTS>\n" +
                "            <PSDZT>10</PSDZT>\n" +
                "        </STRUCT>\n" +
                "    </DETAIL>\n" +
                "</XMLDATA>\n";
        YY154_REP_XML res = ygUtils.praseXml(YY154_REP_XML.class ,xml);
        return res;
    }
    public YY155_REP_XML getSendBillLst(String billId) throws Exception{
        Thread.sleep(5000);
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<XMLDATA>\n" +
                "    <HEAD>\n" +
                "        <JSSJ>20180315/024037/</JSSJ>\n" +
                "        <ZTCLJG>00000</ZTCLJG>\n" +
                "        <CWXX></CWXX>\n" +
                "        <BZXX></BZXX>\n" +
                "    </HEAD>\n" +
                "    <MAIN>\n" +
                "        <SFWJ>1</SFWJ>\n" +
                "        <DCZHPSMXBH>20180214010001119491</DCZHPSMXBH>\n" +
                "        <JLS>6</JLS>\n" +
                "    </MAIN>\n" +
                "    <DETAIL>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119486</PSMXBH>\n" +
                "            <SCPH>LL2769</SCPH>\n" +
                "            <SCRQ>20171001</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20220930</YXRQ>\n" +
                "            <PSL>120</PSL>\n" +
                "            <PSMXTM>20180212000000514278</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139829</DDMXBH>\n" +
                "            <SXH>0</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000000494</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119487</PSMXBH>\n" +
                "            <SCPH>LE7058</SCPH>\n" +
                "            <SCRQ>20170501</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20220430</YXRQ>\n" +
                "            <PSL>180</PSL>\n" +
                "            <PSMXTM>20180212000000514279</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139828</DDMXBH>\n" +
                "            <SXH>1</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000003381</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119488</PSMXBH>\n" +
                "            <SCPH>170814</SCPH>\n" +
                "            <SCRQ>20170814</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20190813</YXRQ>\n" +
                "            <PSL>155</PSL>\n" +
                "            <PSMXTM>20180212000000514280</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139827</DDMXBH>\n" +
                "            <SXH>2</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000003821</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119489</PSMXBH>\n" +
                "            <SCPH>170616</SCPH>\n" +
                "            <SCRQ>20170616</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20190615</YXRQ>\n" +
                "            <PSL>40</PSL>\n" +
                "            <PSMXTM>20180212000000514281</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139826</DDMXBH>\n" +
                "            <SXH>3</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000003823</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119490</PSMXBH>\n" +
                "            <SCPH>GM1710272</SCPH>\n" +
                "            <SCRQ>20171027</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20190930</YXRQ>\n" +
                "            <PSL>2400</PSL>\n" +
                "            <PSMXTM>20180212000000514282</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139825</DDMXBH>\n" +
                "            <SXH>4</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000007252</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119491</PSMXBH>\n" +
                "            <SCPH>3406285</SCPH>\n" +
                "            <SCRQ>20170406</SCRQ>\n" +
                "            <PSMXTMLX>99</PSMXTMLX>\n" +
                "            <YXRQ>20210406</YXRQ>\n" +
                "            <PSL>300</PSL>\n" +
                "            <PSMXTM>20180212000000514283</PSMXTM>\n" +
                "            <DDMXBH>20180212010601139824</DDMXBH>\n" +
                "            <SXH>5</SXH>\n" +
                "            <XSDDH>SO-18001035-2</XSDDH>\n" +
                "            <HCTBDM>CN0000000007372</HCTBDM>\n" +
                "        </STRUCT>\n" +
                "    </DETAIL>\n" +
                "</XMLDATA>\n";
        YY155_REP_XML res = ygUtils.praseXml(YY155_REP_XML.class ,xml);
        for (YY155_REP_DETAIL yy155_rep_detail : res.getDetail()) {
            yy155_rep_detail.setPsmxbh(UUID.randomUUID().toString());
            yy155_rep_detail.setPsdbh(billId);
        }
        return res;
    }

    public YY161_REP_XML getSendBillState(String billId)throws Exception{
        Thread.sleep(5000);
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<XMLDATA>\n" +
                "    <HEAD>\n" +
                "        <JSSJ>20180315/024207/</JSSJ>\n" +
                "        <ZTCLJG>00000</ZTCLJG>\n" +
                "        <CWXX></CWXX>\n" +
                "        <BZXX></BZXX>\n" +
                "    </HEAD>\n" +
                "    <MAIN>\n" +
                "        <SFWJ>1</SFWJ>\n" +
                "        <DCZHPSMXBH>20180214010001119491</DCZHPSMXBH>\n" +
                "        <JLS>6</JLS>\n" +
                "    </MAIN>\n" +
                "    <DETAIL>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119486</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119487</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119488</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119489</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119490</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "        <STRUCT>\n" +
                "            <PSMXBH>20180214010001119491</PSMXBH>\n" +
                "            <PSMXZT>10</PSMXZT>\n" +
                "            <YYYTGS>0</YYYTGS>\n" +
                "            <YYYBGS>0</YYYBGS>\n" +
                "            <YSYTGS>0</YSYTGS>\n" +
                "            <YSYBGS>0</YSYBGS>\n" +
                "        </STRUCT>\n" +
                "    </DETAIL>\n" +
                "</XMLDATA>\n";
        YY161_REP_XML res = ygUtils.praseXml(YY161_REP_XML.class ,xml);
        for (YY161_REP_DETAIL yy161_rep_detail : res.getDetail()) {
            yy161_rep_detail.setPsmxbh(UUID.randomUUID().toString());
            yy161_rep_detail.setPsdbh(billId);
        }
        return res;
    }
}

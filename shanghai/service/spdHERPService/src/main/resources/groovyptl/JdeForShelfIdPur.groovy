import groovy.sql.Sql
import gyqx.spdherp.spdOutPtl.vo.MessageResponse
import org.apache.commons.dbcp2.BasicDataSource
/**
 * Created by cjzyw on 2018/6/29.
 */
class JdeForShelfIdPur {
    static MessageResponse forShelfId(Object... objects){
        def resp = new MessageResponse()
        resp.resultCode = 0
        resp.resultContent = "success"
        def dataSource = new BasicDataSource(driverClassName: "oracle.jdbc.OracleDriver",
                url: 'jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=128.100.200.181)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=JDEMIS)))',
                username: 'spdru', password: 'spdru')
        def spdsql = "SELECT TRIM(商品编码) as erpcode,TRIM(库位) as shelfId,TRIM(生产批号) as code from MVMID.SV_IF_SPD_SHHSBYKC where 商品编码 = ?"
        Sql sql = new Sql(dataSource)
        def jdeShelfInfo = sql.rows(spdsql,[objects[0]])
        if(jdeShelfInfo.size()==0){
            resp.resultCode = -1
            resp.resultContent = "从JDE未找到该产品对应的货位编码"
            resp.data = "empty"
            resp.code = "empty"
            return resp
        }
        def shelfId = jdeShelfInfo.first().shelfId
        def code = jdeShelfInfo.first().code
        resp.data = shelfId
        resp.code = code
        return resp
    }
}

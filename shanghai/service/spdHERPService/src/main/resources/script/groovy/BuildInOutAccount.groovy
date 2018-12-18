
import groovy.sql.Sql
import org.apache.commons.dbcp2.BasicDataSource
import org.apache.commons.lang3.time.DateFormatUtils
import org.apache.commons.lang3.time.DateUtils;
class BuildInOutAcount {
    void testFun( driverClassName,url,username,password){
        /**
         *         driverClassName: "com.mysql.jdbc.Driver",
         url:"jdbc:mysql://127.0.0.1:3306/spd?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
         username: 'admin', password: 'admins'
         */
          def dbOraCfg=
                [

                        driverClassName: driverClassName,
                        url:url,
                        username: username, password: password
                ]
  println(dbOraCfg)
        def dsOra = new BasicDataSource(dbOraCfg)
        def db=new Sql(dsOra)
        def start =null
        def end =null
        def timeBetweenSql ="SELECT handled_start_date,handled_end_date FROM ets_quartz_log_info where last_update_datetime =(SELECT max(last_update_datetime) from ets_quartz_log_info)"
        def timeBetweenRes = db.rows(timeBetweenSql)
        if(timeBetweenRes.size()==0){
            start= DateUtils.parseDate("2017-12-30", Locale.TRADITIONAL_CHINESE, "yyyy-MM-dd")

            end =DateUtils.parseDate("2018-05-01", Locale.TRADITIONAL_CHINESE, "yyyy-MM-dd")
        }
        else{
            start =timeBetweenRes[0].handled_end_date
            end = DateUtils.addDays(start, 30)
            def now =DateUtils.parseDate(DateFormatUtils.format(new Date(),'yyyy-MM-dd'), Locale.TRADITIONAL_CHINESE, "yyyy-MM-dd")
            if(end.compareTo(now)==1){
                end = now
            }

        }
      //  println([start,end])
        def in_strSql="""select UUID() id,m.in_org_id hos_id,m.bill_id,m.in_stock_kind,m.in_stock_type,m.out_org_id,m.out_org_name,m.in_org_id,
                m.in_org_name,m.out_dept_id,m.out_dept_name,m.in_dept_id,m.in_dept_name,m.in_stoc_id,m.out_stoc_id,m.fill_date,
                m.filler,m.auditor,m.audit_date,m.accounter,m.account_date,m.status,list.out_bill_row,list.in_bill_row,list.prov_id,
                list.prov_name,list.goods_id ,list.goods_name,list.goods_gg,list.mfrs_name,list.unit,list.batch_code,list.sterilization_date,
                list.sterilization_code,list.sterilization_end_date,list.expdt_end_date,list.master_code,list.sec_code,list.hibc_code,
                list.certificate_code,IFNULL(list.state,0) state,list.unique_kind is_unique,
                CASE when list.unique_kind=3 then batch.p_row_id else p.p_row_id end p_row_id,
                CASE when list.unique_kind=3 then batch.goods_batch_id else p.goods_batch_id end goods_batch_id,
                CASE when list.unique_kind=3 then batch.in_price else p.in_price end in_price,
                CASE when list.unique_kind=3 then batch.qty else 1 end qty,
                p.unique_code,unique_code epc,
                1 unique_qty,0 is_used,m.last_update_datetime,0 version
                from  in_stock_list list
                left join in_stock m on m.bill_id=list.bill_id 
                left join in_stock_batch batch on batch.bill_id=list.bill_id and list.id=batch.pid and batch.p_row_id=list.in_bill_row
                LEFT JOIN in_stock_unique_code p on p.bill_id=list.bill_id and list.id=p.pid and p.p_row_id=list.in_bill_row
                where m.last_update_datetime between ? and ? """
/*        def out_strSql="""select UUID() id,m.out_org_id hos_id,m.bill_id,m.out_stock_kind,m.out_stock_type,m.out_org_id,m.out_org_name,m.in_org_id,
                m.in_org_name,m.out_dept_id,m.out_dept_name,m.in_dept_id,m.in_dept_name,m.in_stoc_id,m.out_stoc_id,m.patient_name,m.fill_date,
                m.filler,m.auditor,m.audit_date,m.accounter,m.account_date,m.status,list.out_bill_row,list.prov_id,
                list.prov_name,list.goods_id ,list.goods_name,list.goods_gg,list.mfrs_name,list.unit,list.batch_code,list.sterilization_date,
                list.sterilization_code,list.sterilization_end_date,list.expdt_end_date,list.master_code,list.sec_code,list.hibc_code,
                list.certificate_code,IFNULL(list.status,0) state,list.is_unique,
                CASE when list.is_unique=3 then batch.p_row_id else p.p_row_id end p_row_id,
                CASE when list.is_unique=3 then batch.goods_batch_id else p.goods_batch_id end goods_batch_id,
                CASE when list.is_unique=3 then batch.in_price else p.in_price end in_price,
                CASE when list.is_unique=3 then batch.qty else 1 end qty,
                p.unique_code,unique_code epc,
                1 unique_qty,0 is_used,m.last_update_datetime,0 version
                from out_stock_list list
                left join out_stock m on m.bill_id=list.bill_id
                left join out_stock_batch batch on batch.bill_id=list.bill_id and list.id=batch.pid and batch.p_row_id=list.out_bill_row
                LEFT JOIN out_stock_unique_code p on p.bill_id=list.bill_id and list.id=p.pid and p.p_row_id=list.out_bill_row
                where m.last_update_datetime between ? and ?  """*/
        def out_strSql="""select UUID() id,m.out_org_id hos_id,m.bill_id,m.out_stock_kind,m.out_stock_type,m.out_org_id,m.out_org_name,m.in_org_id,
                m.in_org_name,m.out_dept_id,m.out_dept_name,m.in_dept_id,m.in_dept_name,m.in_stoc_id,m.out_stoc_id,m.patient_name,m.fill_date,
                m.filler,m.auditor,m.audit_date,m.accounter,m.account_date,m.status,list.out_bill_row,list.prov_id,
                list.prov_name,list.goods_id ,list.goods_name,list.goods_gg,list.mfrs_name,list.unit,list.batch_code,list.sterilization_date,
                list.sterilization_code,list.sterilization_end_date,list.expdt_end_date,list.master_code,list.sec_code,list.hibc_code,
                list.certificate_code,IFNULL(list.status,0) state,list.is_unique,
                CASE when list.is_unique=3 then batch.p_row_id else p.p_row_id end p_row_id,
                CASE when list.is_unique=3 then batch.goods_batch_id else p.goods_batch_id end goods_batch_id,
                CASE when list.is_unique=3 then batch.in_price else p.in_price end in_price,
                CASE when list.is_unique=3 then batch.qty else 1 end qty,
                p.unique_code,unique_code epc,
                1 unique_qty,0 is_used,m.last_update_datetime,0 version
                from out_stock m
                left join out_stock_list list on m.bill_id=list.bill_id 
                left join out_stock_batch batch on batch.bill_id=list.bill_id and list.id=batch.pid and batch.p_row_id=list.out_bill_row
                LEFT JOIN out_stock_unique_code p on p.bill_id=list.bill_id and list.id=p.pid and p.p_row_id=list.out_bill_row
                where m.last_update_datetime between ? and ?  """
        def in_res=db.rows(in_strSql,[start,end])
        def out_res=db.rows(out_strSql,[start,end])
        println(in_res.size())

        db.withTransaction {
            in_res.each {
                it.last_update_datetime = new Date()
                def selRow
                if (it.getAt("is_unique") == "3") {
                    //非唯一码管理
                    selRow=db.rows("select id,version from in_stock_account where hos_id=:hos_id and bill_id=:bill_id and goods_id=:goods_id and in_bill_row=:in_bill_row and ifnull(batch_code,'')=ifnull(:batch_code,'') and goods_batch_id=:goods_batch_id and in_price=:in_price",it)
                }else{
                    selRow=db.rows("select id,version from in_stock_account where hos_id=:hos_id and bill_id=:bill_id and goods_id=:goods_id and in_bill_row=:in_bill_row and ifnull(batch_code,'')=ifnull(:batch_code,'') and goods_batch_id=:goods_batch_id and ifnull(unique_code,'')=ifnull(:unique_code,'') and in_price=:in_price",it)
                }
                if (selRow.size()>0) {
                    //如果存在更新
                    it.oldid = selRow[0].id
                    it.oldversion = selRow[0].version
                    db.execute("""update in_stock_account set `hos_id`=:hos_id,`bill_id`=:bill_id,`in_stock_kind`=:in_stock_kind,`in_stock_type`=:in_stock_type,`out_org_id`=:out_org_id,`out_org_name`=:out_org_name,`in_org_id`=:in_org_id,`in_org_name`=:in_org_name,
                                        `out_dept_id`=:out_dept_id,`out_dept_name`=:out_dept_name,`in_dept_id`=:in_dept_id,`in_dept_name`=:in_dept_name,`in_stoc_id`=:in_stoc_id,`out_stoc_id`=:out_stoc_id,`fill_date`=:fill_date,`filler`=:filler,`auditor`=:auditor,
                                        `audit_date`=:audit_date,`accounter`=:accounter,`account_date`=:account_date,`status`=:status,`out_bill_row`=:out_bill_row,`in_bill_row`=:in_bill_row,`prov_id`=:prov_id,`prov_name`=:prov_name,`goods_id`=:goods_id,
                                        `goods_name`=:goods_name,`goods_gg`=:goods_gg,`mfrs_name`=:mfrs_name,`unit`=:unit,`batch_code`=:batch_code,`sterilization_date`=:sterilization_date,`sterilization_code`=:sterilization_code,
                                        `sterilization_end_date`=:sterilization_end_date,`expdt_end_date`=:expdt_end_date,`master_code`=:master_code,`sec_code`=:sec_code,`hibc_code`=:hibc_code,`certificate_code`=:certificate_code,`state`=:state,
                                        `p_row_id`=:p_row_id,`goods_batch_id`=:goods_batch_id,`in_price`=:in_price,`qty`=:qty,`unique_code`=:unique_code,`epc`=:epc,`unique_qty`=:unique_qty,`is_used`=:is_used,`last_update_datetime`=:last_update_datetime,`version`=version+1 where id=:oldid and version=:oldversion""", it)
                    println("update in_stock_account:======="+it.getAt("goods_name"))
                }else {
                    //不存在才插入
                    db.execute("insert into in_stock_account (" +
                            "`id`,`hos_id`,`bill_id`,`in_stock_kind`,`in_stock_type`,`out_org_id`,`out_org_name`,`in_org_id`,`in_org_name`,`out_dept_id`,`out_dept_name`,`in_dept_id`,`in_dept_name`,`in_stoc_id`,`out_stoc_id`,`fill_date`,`filler`,`auditor`,`audit_date`,`accounter`,`account_date`,`status`,`out_bill_row`,`in_bill_row`,`prov_id`,`prov_name`,`goods_id`,`goods_name`,`goods_gg`,`mfrs_name`,`unit`,`batch_code`,`sterilization_date`,`sterilization_code`,`sterilization_end_date`,`expdt_end_date`,`master_code`,`sec_code`,`hibc_code`,`certificate_code`,`state`,`p_row_id`,`goods_batch_id`,`in_price`,`qty`,`unique_code`,`epc`,`unique_qty`,`is_used`,`last_update_datetime`,`version`) " +
                            "values(" +
                            ":id,:hos_id,:bill_id,:in_stock_kind,:in_stock_type,:out_org_id,:out_org_name,:in_org_id,:in_org_name,:out_dept_id,:out_dept_name,:in_dept_id,:in_dept_name,:in_stoc_id,:out_stoc_id,:fill_date,:filler,:auditor,:audit_date,:accounter,:account_date,:status,:out_bill_row,:in_bill_row,:prov_id,:prov_name,:goods_id,:goods_name,:goods_gg,:mfrs_name,:unit,:batch_code,:sterilization_date,:sterilization_code,:sterilization_end_date,:expdt_end_date,:master_code,:sec_code,:hibc_code,:certificate_code,:state,:p_row_id,:goods_batch_id,:in_price,:qty,:unique_code,:epc,:unique_qty,:is_used,:last_update_datetime,:version)", it)
                    println("insert in_stock_account:======="+it.getAt("goods_name"))
                }
            }
            out_res.each {
                if (it.hos_id == null) return;
                it.last_update_datetime = new Date()
                def selRow
                if (it.getAt("is_unique") == "3") {
                    //非唯一码管理
                    selRow=db.rows("select id,version from out_stock_account where hos_id=:hos_id and bill_id=:bill_id and goods_id=:goods_id and out_bill_row=:out_bill_row and ifnull(batch_code,'')=ifnull(:batch_code,'') and goods_batch_id=:goods_batch_id and in_price=:in_price",it)
                }else{
                    selRow=db.rows("select id,version from out_stock_account where hos_id=:hos_id and bill_id=:bill_id and goods_id=:goods_id and out_bill_row=:out_bill_row and ifnull(batch_code,'')=ifnull(:batch_code,'') and goods_batch_id=:goods_batch_id and ifnull(unique_code,'')=ifnull(:unique_code,'') and in_price=:in_price",it)
                }

                if (selRow.size()>0) {
                    //如果存在更新
                    it.oldid = selRow[0].id
                    it.oldversion = selRow[0].version
                    db.execute("update out_stock_account set `hos_id`=:hos_id,`bill_id`=:bill_id,`out_stock_kind`=:out_stock_kind,`out_stock_type`=:out_stock_type,`out_org_id`=:out_org_id," +
                            "`out_org_name`=:out_org_name,`in_org_id`=:in_org_id,`in_org_name`=:in_org_name,`out_dept_id`=:out_dept_id,`out_dept_name`=:out_dept_name,`in_dept_id`=:in_dept_id," +
                            "`in_dept_name`=:in_dept_name,`in_stoc_id`=:in_stoc_id,`out_stoc_id`=:out_stoc_id,`patient_name`=:patient_name,`fill_date`=:fill_date,`filler`=:filler,`auditor`=:auditor," +
                            "`audit_date`=:audit_date,`accounter`=:accounter,`account_date`=:account_date,`status`=:status,`out_bill_row`=:out_bill_row,`prov_id`=:prov_id,`prov_name`=:prov_name," +
                            "`goods_id`=:goods_id,`goods_name`=:goods_name,`goods_gg`=:goods_gg,`mfrs_name`=:mfrs_name,`unit`=:unit,`batch_code`=:batch_code,`sterilization_date`=:sterilization_date," +
                            "`sterilization_code`=:sterilization_code,`sterilization_end_date`=:sterilization_end_date,`expdt_end_date`=:expdt_end_date,`master_code`=:master_code,`sec_code`=:sec_code," +
                            "`hibc_code`=:hibc_code,`certificate_code`=:certificate_code,`state`=:state,`p_row_id`=:p_row_id,`goods_batch_id`=:goods_batch_id,`in_price`=:in_price,`qty`=:qty," +
                            "`unique_code`=:unique_code,`epc`=:epc,`unique_qty`=:unique_qty,`is_used`=:is_used,`last_update_datetime`=:last_update_datetime,`version`=version+1 where id=:oldid and version=:oldversion", it)
                    println("update out_stock_account:======="+it.getAt("goods_name"))

                }else {
                    //不存在才插入
                    db.execute("insert into out_stock_account (`id`,`hos_id`,`bill_id`,`out_stock_kind`,`out_stock_type`,`out_org_id`,`out_org_name`,`in_org_id`,`in_org_name`,`out_dept_id`,`out_dept_name`,`in_dept_id`,`in_dept_name`,`in_stoc_id`,`out_stoc_id`,`patient_name`,`fill_date`,`filler`,`auditor`,`audit_date`,`accounter`,`account_date`,`status`,`out_bill_row`,`prov_id`,`prov_name`,`goods_id`,`goods_name`,`goods_gg`,`mfrs_name`,`unit`,`batch_code`,`sterilization_date`,`sterilization_code`,`sterilization_end_date`,`expdt_end_date`,`master_code`,`sec_code`,`hibc_code`,`certificate_code`,`state`,`p_row_id`,`goods_batch_id`,`in_price`,`qty`,`unique_code`,`epc`,`unique_qty`,`is_used`,`last_update_datetime`,`version`) " +
                            "values(" +
                            ":id,:hos_id,:bill_id,:out_stock_kind,:out_stock_type,:out_org_id,:out_org_name,:in_org_id,:in_org_name,:out_dept_id,:out_dept_name,:in_dept_id,:in_dept_name,:in_stoc_id,:out_stoc_id,:patient_name,:fill_date,:filler,:auditor,:audit_date,:accounter,:account_date,:status,:out_bill_row,:prov_id,:prov_name,:goods_id,:goods_name,:goods_gg,:mfrs_name,:unit,:batch_code,:sterilization_date,:sterilization_code,:sterilization_end_date,:expdt_end_date,:master_code,:sec_code,:hibc_code,:certificate_code,:state,:p_row_id,:goods_batch_id,:in_price,:qty,:unique_code,:epc,:unique_qty,:is_used,:last_update_datetime,:version)", it)
                    println("insert out_stock_account:======="+it.getAt("goods_name"))

                }
            }
            def id=UUID.randomUUID().toString()
            db.execute("""insert into ets_quartz_log_info(id,handled_start_date,handled_end_date,last_update_datetime,version) 
                          values($id,$start,$end,sysDate(),0)                         

                         """ )
        }

    }

}

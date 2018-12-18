package gyqx.spdherp.productsInfos.vo;

import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;

/**
 * @Auther Liangwu
 * @Date 17-11-3 下午4:14
 */
public class HosGoodsImageVo {
    /**
     * ID
     */
    @Id
    @Size(max=36)
    @Column(name="id")
    @Title("ID")
    private String id ;
    /**
     * 商品ID
     */
    @Size(max=36)
    @JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
    @Title("商品ID")
    private String hosGoodsId ;
    /**
     * 附件路径1
     */
    @Size(max=256)
    @Column(name="file_path1")
    @Title("附件路径1")
    private String filePath1 ;
    /**
     * 附件路径2
     */
    @Size(max=256)
    @Column(name="file_path2")
    @Title("附件路径2")
    private String filePath2 ;
    /**
     * 附件路径3
     */
    @Size(max=256)
    @Column(name="file_path3")
    @Title("附件路径3")
    private String filePath3 ;
    /**
     * 附件路径4
     */
    @Size(max=256)
    @Column(name="file_path4")
    @Title("附件路径4")
    private String filePath4 ;
    /**
     * 附件路径5
     */
    @Size(max=256)
    @Column(name="file_path5")
    @Title("附件路径5")
    private String filePath5 ;
    /**
     * 附件路径6
     */
    @Size(max=256)
    @Column(name="file_path6")
    @Title("附件路径1")
    private String filePath6 ;
    /**
     * 数据版本
     */
    @Column(name="version")
    @Title("数据版本")
    private int version ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHosGoodsId() {
        return hosGoodsId;
    }

    public void setHosGoodsId(String hosGoodsId) {
        this.hosGoodsId = hosGoodsId;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getFilePath4() {
        return filePath4;
    }

    public void setFilePath4(String filePath4) {
        this.filePath4 = filePath4;
    }

    public String getFilePath5() {
        return filePath5;
    }

    public void setFilePath5(String filePath5) {
        this.filePath5 = filePath5;
    }

    public String getFilePath6() {
        return filePath6;
    }

    public void setFilePath6(String filePath6) {
        this.filePath6 = filePath6;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

<template>
    <div id="provGoodsHoslist" style="height: 100%;">
        <el-row  style="height: 100%;">
            <el-col :span="4" style="height: 98%;" >
                 <div style="margin-bottom: 10px">
                    <el-input v-model="filters.kindName" placeholder="请输入类型名称" icon="search" :on-icon-click="searchs"  @keyup.enter.native="searchs"    >
                    </el-input>
                </div>

                <el-tree :data="kindsValue" node-key="id"
                         :default-expanded-keys="expadoIds"
                         :props="defaultProps"
                         :highlight-current="true"
                         @node-click="handleNodeClick"  >
                </el-tree>
            </el-col>
            <el-col :span="20" class="toolbar" style="padding-left: 5px; height: calc(100% - 25px); ">
                <div style="margin-bottom: 5px;">
                    <el-form :inline="true">
                        <el-form-item label="商品名称">
                            <el-input v-model="goodFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input v-model="goodFilters.goodsGg" placeholder="请输入规格型号"   > </el-input>
                        </el-form-item>
                        <el-form-item label="供应商">
                            <el-input v-model="goodFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>
                         <el-form-item label="是否激活" >
                              <el-select v-model="goodFilters.flag"  filterable clearable placeholder="请选择" style="width:100px;">
                                  <el-option
                                          v-for="item in flags"
                                          :key="item.value"
                                          :label="item.label"
                                          :value="item.value">
                                  </el-option>
                              </el-select>
                          </el-form-item>
                        <el-button  type="primary"  @click="searchGoods(null)">查询</el-button>
                        <el-button  type="primary"  @click="connect(null)">添加商品</el-button>
                      <!--  <el-button  type="primary"  @click="disConnect(null)">解除关联</el-button> -->
                    </el-form>
                </div>
                <el-table highlight-current-row :data="goodList" style="width: 100%; height: calc(100% - 85px);" ref="multipleTable4Main" row-key='id' @selection-change="handleSelectionChange4Main" v-loading="goodsLoading" border fit :default-sort="{prop: 'provGoodsGoodsName', order: 'descending'}" height="outer">
                          <!--
                            <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>
                            -->
                            <!--供货目录只列出供应商的 商品信息， 对码不在此处实现 -->
                            <!--
                            <el-table-column prop="hisCode"   min-idth="85" label="医院商品编号" header-align="center" >
                            </el-table-column>
                            -->
                            <el-table-column label="操作" width="180" header-align="center" align="center" fixed="right">
                                <template slot-scope="scope">
                                    <el-button-group>
                                    	<el-button type="primary" size="mini" @click="actGood(scope.row)" icon="plus" v-if="scope.row.flag==0" >激活</el-button>
                                		<el-button type="primary" size="mini" @click="goodEdit(scope.row)" icon="edit" v-if="scope.row.flag==1">编辑</el-button>
                                        <el-button type="danger" size="mini" @click="delGood(scope.row)" icon="delete" v-if="scope.row.flag==1">删除</el-button>
                                        <el-button type="info" size="mini" icon="el-icon-picture" @click="handleGoodsImg(scope.row)" v-if="scope.row.flag==1">图片({{scope.row.imgCount}})</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <el-table-column prop="code" label="商品编号" header-align="center"  width="80" >
                            </el-table-column>

                            <el-table-column prop="goodsName"   min-idth="200" label="商品名称" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsGg"   min-idth="200" label="规格型号" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <el-table-column prop="goodsDescFile"   min-idth="170" label="说明书" width="150" header-align="center" show-overflow-tooltip >
                              <template slot-scope="scope">
                                    <el-button-group>
                                      <el-button @click="showDescDia(scope.row)" type="info" size="mini" icon="upload">上传</el-button>
                                      <el-button v-if="scope.row.goodsDescFile!=null" @click="downloadDesc(scope.row)" type="info" size="mini" icon="el-icon-picture">下载</el-button>
                                    </el-button-group>
                                </template>
                            </el-table-column>
                            <el-table-column prop="made"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="unit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="mfrsName"  header-align="center" width="150"  label="生产厂商" >
                            </el-table-column>

                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>
                            <el-table-column prop="brand"   min-idth="200" label="品牌" width="150" header-align="center" show-overflow-tooltip >
                            </el-table-column>
                            <!--<el-table-column type="selection" width="40"></el-table-column>-->
                </el-table>
                <el-col :span="24" class="toolbar">
                    <el-pagination @size-change="handleGoodsSizeChange" 
                        @current-change="handleGoodsCurrentChange" 
                        :current-page="goodsPage" 
                        :page-sizes="[10, 20, 50, 100]" 
                        :page-size="goodsPageSize" 
                        layout="total, sizes, prev, pager, next" 
                        :total="goodsTotal" style="float:right;" small>
                   </el-pagination>
                </el-col>
            </el-col>
        </el-row>

        <el-dialog title="产品说明书上传" :visible="descDia" :show-close="false" :close-on-click-modal="false" size='tiny'>
          <div style="text-align:center;margin-bottom:10px;"><span style="font-size:12px;color:red">注：以最后上传的文件有效,上传文件后请点击保存！</span></div>
          <el-upload style="text-align:center;" class="pic-uploader" ref="upload" :headers="userInfo" :action="uploadURL" :auto-upload="true" :show-file-list="false" :multiple="false" :on-success="descUploadSuccess" :on-error="handleUploadErr">

            <i class="el-icon-plus pic-uploader-icon"></i>
          </el-upload>
          <div style="text-align:center;"><span>文件: {{this.goods.fileName}}</span></div>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="descDia = false">取消</el-button>
            <el-button type="primary" @click.native="descUploadSave">保存</el-button>
          </div>
        </el-dialog>

        <!-- pop 弹出getProGoods4HosList 供货目录供添加医院产品并关联使用 -->
        <el-dialog :title="popTitle" close="onClose" :visible.sync="popVisible" :close-on-click-modal="false" size='large'>
             <el-form :inline="true">
                        <el-form-item label="商品名称">
                            <el-input v-model="popFilters.goodsName" placeholder="请输入商品名"   ></el-input>
                        </el-form-item>
                        <el-form-item label="商品规格">
                            <el-input v-model="popFilters.goodsGg" placeholder="请输入规格"   > </el-input>
                        </el-form-item>
                        <el-form-item label="供应商">
                            <el-input v-model="popFilters.provName" placeholder="请输入供应商"   > </el-input>
                         </el-form-item>

                         <el-switch :width="80"
                            v-model="popFilters.isAll"
                             active-value="1" inactive-value="0"
                             active-color="#13ce66" active-text="全部" inactive-text="未导入" inactive-color="#ff4949">
                            </el-switch>
                        <el-button  type="primary"  @click="searchPopGoods(null)">查询</el-button>
                    </el-form>
            <template>
                <el-table
                    ref="multipleTable" :data="popGoodList"  border tooltip-effect="dark" row-key='id'
                    style="width: 100%" @selection-change="handlePopSelectionChange">
                    <el-table-column  type="selection" :reserve-selection="true" width="55"> </el-table-column>

                            <el-table-column prop="provGoodsName"   min-idth="85" label="商品名称" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="provGoodsGg"   min-idth="85" label="商品规格" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="provGoodsMade"   min-idth="85" label="产地" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="provGoodsUnit"   min-idth="85" label="单位" header-align="center" >
                            </el-table-column>
                            <el-table-column prop="provGoodsMfrsName"  header-align="center" width="150"  label="生产厂商" >
                            </el-table-column>
                            <el-table-column prop="provName"  header-align="center" width="150"  label="供应商" show-overflow-tooltip sortable>
                            </el-table-column>

                </el-table>
                    <el-pagination @size-change="handlePopSizeChange" @current-change="handlePopCurrentChange" :current-page="popPage" :page-sizes="[10, 20, 50, 100]" :page-size="popPageSize" layout="total, sizes, prev, pager, next"
                    :total="popTotal" style="float:right;" small>
                    </el-pagination>
                <div style="margin-top: 20px">

                    <el-button @click="toggleSelection()">取消选择</el-button>
                    <el-button @click="popSubmit()">确定</el-button>
                </div>
            </template>
        </el-dialog>
        	<!--编辑界面-->
				<el-dialog :title="modelTitle" close="onClose" :visible.sync="editFormVisible" :close-on-click-modal="false" >
                    <el-form :model="good"  label-width="100px" :rules="editFormRules" ref="editForm"  class="el-form-item-nomsg">
                            <el-row>
                                <el-col>
                                    <el-form-item label="医院商品名称" prop="goodsName" :error="errors.goodsNameErr" >
                                        <el-input v-model="good.goodsName"  placeholder="请输入商品名称"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="商品俗称" prop="generalName"  >
                                        <el-input v-model="good.generalName"  placeholder="请输入商品俗称"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="商品描述" prop="goodsDesc"  >
                                        <el-input v-model="good.goodsDesc"  placeholder="请输入商品描述"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="供应商名称" prop="provName"  >
                                        <el-input v-model="good.provName" :disabled="true"   placeholder="请输入商品名称"></el-input>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :span="8">
                                    <el-form-item label="商品分类11" prop="hosKindName" >
                                        <el-input v-model="good.hosKindName" :disabled="true"  ></el-input>
                                    </el-form-item>
                                </el-col>-->
                                <el-col :span="12">
                                    <el-form-item label="所属分类">
                                            <el-cascader v-model="fl" placeholder="请选择分类"
                                                 :show-all-levels="false"
                                                :options="kv4c" filterable
                                                :props="kv4cProps"
                                                >
                                                </el-cascader>
                                        </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="8">
                                    <el-form-item label="医院商品编号" prop="code" :error="errors.codeErr">
                                                <el-input v-model="good.code" placeholder="请输入商品编号" ></el-input>
                                    </el-form-item>
                                </el-col>

                                  <el-col :span="16">
                                        <el-form-item label="生产厂家" prop="mfrsName" >
                                                <el-input v-model="good.mfrsName" :disabled="true" placeholder="请输入商品编号" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>

                            <el-row>
                                <el-col :span="8">
                                    <el-form-item label="产地" prop="made" :error="errors.madeErr">
                                                <el-input v-model="good.made"  placeholder="请输入产地"></el-input>
                                    </el-form-item>
                                </el-col>
                                 <el-col :span="16">
                                    <el-form-item label="规格型号" prop="goodsGg" :error="errors.goodsGgErr">
                                        <el-input v-model="good.goodsGg"  placeholder="请输入规格型号"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="出厂编码">
                                                    <el-input placeholder="请输入厂家出厂编码" v-model="good.mfrsCode" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                        <el-form-item label="单位" prop="unit" :error="errors.unitErr">
                                                    <el-input placeholder="请输入单位" v-model="good.unit" ></el-input>
                                        </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                        <el-form-item label="品牌" prop="brand">
                                                    <el-input v-model="good.brand" :disabled="true" ></el-input>
                                        </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="包装" prop="packeage" :error="errors.packeageErr">
                                                <el-input placeholder="请输入包装" v-model.number="good.packeage" ></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item label="统一码">
                                                <el-input placeholder="请输入统一码" v-model="good.masterCode" ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                   <!--
                                    <el-form-item label="68分类">
                                                <el-input placeholder="请输入68分类" v-model="good.kind68code" ></el-input>
                                    </el-form-item>
                                    -->
                                      <el-form-item label="药监分类" prop="kind68code" >
                                            <el-select v-model="kind68code" filterable placeholder="请选择"
                                            style='width:100%' >
                                                <el-option
                                                    v-for="item in dict68s"
                                                    :key="item.val"
                                                    :label="item.ename"
                                                    :value="item.val">
                                                    <span style="float: left">{{ item.ename }}</span>
                                                        <span style="float: right; color: #8492a6; font-size: 13px">{{ item.val }}</span>
                                                </el-option>
                                            </el-select>
                                    </el-form-item>
                                </el-col>

                            </el-row>
                            <!--
                            <el-row>
                                <el-col :span="12">
                                         <el-form-item label="类别">
                                                    <el-input placeholder="请输入类别" v-model="good.kindCode" ></el-input>
                                         </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                        <el-form-item label="类别属性">
                                                        <el-input placeholder="请输入类别属性" v-model="good.lbsx" ></el-input>
                                        </el-form-item>
                                </el-col>
                            </el-row>
                            -->
                            <el-row>
                                <!--<el-col :span="12">
                                        <el-form-item label="类别属性">
                                            <el-input placeholder="请输入类别属性" v-model="good.lbsx" ></el-input>
                                        </el-form-item>
                                </el-col>-->
                                <!--
                                    <el-col :span="12">
                                        <el-form-item label=" 备注信息">
                                            <el-input placeholder="请输入 备注信息" v-model="good.remark" ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    -->
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                        <el-form-item label="价格"  prop="price" :error="errors.priceErr">
                                            <el-input placeholder="请输入类别属性" v-model.number="good.price" ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="阳光采购编码">
                                            <el-input placeholder="请输入阳光采购编码" v-model="good.hitCode" ></el-input>
                                        </el-form-item>
                                    </el-col>
                            </el-row>

                            <el-row>
                                <el-col :span="12">
                                        <el-form-item label=" 阳光采购价" prop="hitPrice" :error="errors.hitPriceErr">
                                            <el-input placeholder="请输入阳光采购价" v-model.number="good.hitPrice" ></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="erp编码">
                                            <el-input placeholder="请输入erp编码" v-model="good.erpCode" ></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            <el-row>

                            <el-col :span="12">
                              <el-form-item label="平台编码">
                                <el-input placeholder="请输入平台编码" v-model="good.spdGoodsCode" ></el-input>
                              </el-form-item>
                            </el-col>
                              <el-col :span="12">
                                <el-form-item label="HIS销售价"  prop="hisPrice" :error="errors.hisPriceErr">
                                  <el-input placeholder="请输入HIS销售价" v-model.number="good.hisPrice" ></el-input>
                                </el-form-item>
                              </el-col>
                            </el-row>
                            <el-row>
                              <el-col :span="12">
                                  <el-form-item label="医保名称" prop="miName">
                                    <el-input placeholder="请输入医保名称" v-model="good.miName" ></el-input>
                                </el-form-item>
                              </el-col>
                              <el-col :span="12">
                                <el-form-item label="医保编码" prop="miCode">
                                  <el-input placeholder="请输入医保编码" v-model="good.miCode" ></el-input>
                                </el-form-item>
                              </el-col>
                            </el-row>
                                <el-row>

                                                <el-col :span="12">
                                                <el-form-item label="税率" prop="taxRate" >
                                                    <el-select v-model="taxRate" filterable placeholder="请选择"  style="width:100%">
                                                        <el-option
                                                            v-for="item in SL"
                                                            :key="item.ename"
                                                            :label="item.val"
                                                            :value="item.ename">
                                                        </el-option>
                                                </el-select>
                                            </el-form-item>
                                            </el-col>
                                             <el-col :span="12">
                                                 <el-form-item label="唯一码管理策略" prop="uniqueCodeStrategy" >
                                                         <el-select v-model="uniqueCodeStrategy" filterable placeholder="请选择"   style="width:100%">
                                                                <el-option
                                                                    v-for="item in dictUCodeStrategys"
                                                                    :key="item.ename"
                                                                    :label="item.ename"
                                                                    :value="item.val">
                                                                </el-option>
                                                        </el-select>
                                                    </el-form-item>
                                             </el-col>

                                        </el-row>

                                        <el-row>

                                            <el-col >
                                                <el-form-item label="备注信息">
                                                    <el-input placeholder="请输入备注信息" v-model="good.remark" ></el-input>
                                                </el-form-item>
                                            </el-col>

                                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="采购模式" prop="purMode" :error="errors.purModeErr">
                                    <el-select v-model="good.purMode" filterable placeholder="请选择" :clearable="true" >
                                        <el-option
                                                v-for="item in purModes"
                                                :key="item.val"
                                                :label="item.ename"
                                                :value="item.val">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="是否计量" prop="isMetering">
                                    <el-radio-group v-model="good.isMetering" readonly >
                                        <el-radio-button label="1">是</el-radio-button>
                                        <el-radio-button label="0">否</el-radio-button>
                                    </el-radio-group>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-form-item label="是否收费">
                                <el-checkbox v-model="good.charging" true-label="1" false-label="0"><span style="font-size:12px">{{good.charging === '1'?"收费":"不收费"}}</span></el-checkbox>
                            </el-form-item>
                        </el-row>
                    </el-form>

					<div slot="footer" class="dialog-footer">
						<el-button @click.native="editFormVisible = false">取消</el-button>
						<el-button type="primary" @click.native="editSubmit" :loading="editLoading">保存</el-button>
					</div>
				</el-dialog>

        <el-dialog :title="dlgGoodsImgTitle" :visible.sync="dlgGoodsImgVisible" v-loading="uploadDlgLoading">
          <div style="text-align:center;margin-bottom:10px;"><span style="font-size:12px;color:grey">注：上传图片后请点击保存！</span></div>
          <el-row>
            <!-- v-for="i in 6" :span="8" :key="i"  -->
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload0" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[0]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess0" :on-change="handleLocalAdd0" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[0]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[0]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[0]">
                  <img :src="goodsImgs.tUrls[0]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[0]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 0)" :loading="delBtnLoading[0]">删除</el-button>
            </el-col>
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload1" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[1]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess1" :on-change="handleLocalAdd1" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[1]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[1]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[1]">
                  <img :src="goodsImgs.tUrls[1]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[1]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 1)" :loading="delBtnLoading[1]">删除</el-button>
            </el-col>
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload2" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[2]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess2" :on-change="handleLocalAdd2" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[2]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[2]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[2]">
                  <img :src="goodsImgs.tUrls[2]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[2]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 2)" :loading="delBtnLoading[2]">删除</el-button>
            </el-col>
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload3" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[3]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess3" :on-change="handleLocalAdd3" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[3]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[3]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[3]">
                  <img :src="goodsImgs.tUrls[3]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[3]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 3)" :loading="delBtnLoading[3]">删除</el-button>
            </el-col>
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload4" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[4]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess4" :on-change="handleLocalAdd4" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[4]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[4]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[4]">
                  <img :src="goodsImgs.tUrls[4]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[4]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 4)" :loading="delBtnLoading[4]">删除</el-button>
            </el-col>
            <el-col class="goods-img" :span="8">
              <el-upload class="pic-uploader" ref="imgUpload5" accept=".bmp,.jpg,.png,.pdf" :headers="userInfo" :class="{'upload-background':!goodsImgs.origUrls[5]}" :action="uploadURL" :auto-upload="true" :show-file-list="true" :multiple="false" :on-success="handleUploadSuccess5" :on-change="handleLocalAdd5" :before-upload="beforeUpload" :on-error="handleUploadErr">
                <label :class="{'item-status-label':goodsImgs.origUrls[5]}">
                  <i :class="{'upload-success el-icon-check':goodsImgs.origUrls[5]}"></i>
                </label>
                <div v-if="goodsImgs.origUrls[5]">
                  <img :src="goodsImgs.tUrls[5]" class="pic">
                </div>
                <i v-else class="el-icon-plus pic-uploader-icon"></i>
              </el-upload>
              <el-button v-if="goodsImgs.origUrls[5]" type="text" size="mini" icon="delete" @click.native="handleDelHosGoodsImage(goodsImgs.id, 5)" :loading="delBtnLoading[5]">删除</el-button>
            </el-col>
          </el-row>
          <div slot="footer" class="dialog-footer">
            <el-button @click.native="dlgGoodsImgVisible = false">取消</el-button>
            <el-button type="primary" @click.native="handleDlgUploadConfirm" :loading="uploadBtnLoading">保存</el-button>
          </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    return {
      uploadURL: '/platformService/sys/upload/file/goods/',
      picURLPrefix: '/upload/',
      baseURL: '/spdHERPService/productsInfos/hosGoodsImage/',
      goodsImgs: {
        id: '',
        hosGoodsId: '',
        origUrls: Array(6).fill(''),
        tUrls: Array(6).fill(''),
        // uploadSuccess: Array(6).fill(false),
        version: 0
      },
      goods: {},
      descDia: false,
      flags: [{
        value: '1',
        label: '是'
      }, {
        value: '0',
        label: '否'
      }],
      dlgGoodsImgVisible: false,
      dlgGoodsImgTitle: '',
      allowedFileTypes: ['image/bmp', 'image/jpeg', 'image/png', 'application/pdf'],
      uploadBtnLoading: false,
      uploadDlgLoading: true,
      delBtnLoading: Array(6).fill(false),
      imgAddMode: true,

      defaultProps: {
        children: 'children',
        label: 'label',
        value: 'id',
        pId: 'pid',
        code: 'code',
        hosId: 'hosId',
        state: 'state'
      },
      kv4cProps: {
        children: 'children',
        label: 'label',
        value: 'id'
      },
      filters: {
        kindName: null,
        hosId: null
      },
      kindsValue: [],
      expadoIds: [null],
      loading: 'false',
      pname: '商品分类',
      selectedNode: null,
      // 以下为右侧参数
      goodFilters: {
        goodsName: null,
        goodsGg: null,
        provName: null,
        lbsx: null,
        flag: null,
        ids: null
      },
      goodsLoading: false,
      goodList: [],
      goodsTotal: 0,
      goodsPageSize: 10,
      goodsPage: 1,
      multipleSelection4Main: [],
      // pop 参数
      popTitle: null,
      popVisible: false,
      popFilters: {
        goodsName: null,
        goodsGg: null,
        provName: null,
        isAll: '1'
      },
      popGoodList: [],
      popTotal: 0,
      popPageSize: 10,
      popPage: 1,
      multipleSelection4Pop: [],
      // 编辑 参数
      modelTitle: '',
      editLoading: false,
      good: {},
      editFormVisible: false,
      dict68s: [],
      kind68code: '',
      uniqueCodeStrategy: '',
      dictUCodeStrategys: [],
      hosKindsCodes: [],
      kindCode: '',
      SL: [],
      taxRate: '',
      kv4c: [],
      isMetering: null,
      purMode: '',
      purModes: [],

      editFormRules: {
        goodsName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
        code: [{ required: true, message: '请输入商品编号', trigger: 'blur' }],
        goodsGg: [{ required: true, message: '请输入商品规格', trigger: 'blur' }],
        unit: [{ required: true, message: '请输入商品单位', trigger: 'blur' }],
        mfrsId: [{ required: true, message: '请选择生产厂商', trigger: 'blur' }],
        made: [{ required: true, message: '请输入产地', trigger: 'blur' }],
        purMode: [{ required: true, message: '请选择采购模式', trigger: 'blur' }],
        packeage: [
          {
            type: 'number',
            required: false,
            message: '请输入包装，必须为数字',
            trigger: 'blur'
          } //
        ],
        hitPrice: [
          {
            type: 'number',
            required: false,
            message: '请输入阳光采购价，必须为数字',
            trigger: 'blur'
          } // hitPrice
        ],
        price: [
          {
            type: 'number',
            required: false,
            message: '请输入价格，必须为数字',
            trigger: 'blur'
          } //
        ],
        hisPrice: [
          {
            type: 'number',
            required: false,
            message: '请输入HIS销售价，必须为数字',
            trigger: 'blur'
          } //
        ]
      },
      errors: {
        goodsNameErr: '',
        codeErr: '',
        goodsGgErr: '',
        unitErr: '',
        mfrsIdErr: '',
        madeErr: '',
        packeageErr: '',
        hitPriceErr: '',
        hisPriceErr: '',
        purModeErr: '',
        priceErr: ''
      }
    }
  },
  computed: {
    userInfo () {
      return {
        'X-US': this.user.xus
      }
    },
    fl: {
      get: function () {
        let res = []
        if (this.good.hosKindLevelCode) {
          let lcode =
            this.good.hosKindLevelCode.indexOf(':') > 0
              ? this.good.hosKindLevelCode.split(':')[1]
              : this.good.hosKindLevelCode
          //  let lcode= this.good.hosKindLevelCode.split(":")[1];
          let path = lcode.split('.')
          if (path.length > 1) {
            // var i = 0
            // for (i; i < path.length; i++) {
            //   let tempArr = path.slice(0, i + 1)
            //   res.push(tempArr.join('.'))
            // }
            //  res.push(this.good.lbsx);
            
            res = path
          } else {
            res = path
          }
        }
        return res
      },
      set: function (pathArr) {
        let res = ''
        if (pathArr.length > 0) {
          res = pathArr[pathArr.length - 1]
        }
        // this.good.hosKindLevelCode =res.split("|")[0]+":"+ res;
        let path = res.split('|')[0] + ':'
        pathArr.forEach(v => (path = path + v + '.'))
        //     alert(path.substr(0,path.length-1));
        this.good.lbsx = res
        this.good.hosKindLevelCode = path
      }
    }
  },
  methods: {
    getKinds: function () {
      // this.loading=true;
      let param = {
        orderBy: '',
        queryObject: { hosId: this.filters.hosId },
        page: 0,
        rows: 0
      }
      this.axios
        .post(`/spdHERPService/productsInfos/hosKindcode/list4tree`, param)
        .then(res => {
          this.kindsValue = res.data.data
          this.kv4c = res.data.data[0].children
          // this.loading=false;
        })
    },
    downloadDesc (row) {
      if (!row.goodsDescFile || !row.fileName) {
        this.$message.error('请先上传产品说明书')
        return
      }
      window.location.href = '/platformService/sys/upload/downFile?url=' + row.goodsDescFile + '&fileName=' + row.fileName
    },
    showDescDia (row) {
      this.descDia = true
      this.goods = row
    },
    searchs: function () {
      this.loading = true
      let param = {
        orderBy: '',
        queryObject: {
          hosId: this.filters.hosId,
          kindName: this.filters.kindName
        },
        page: 0,
        rows: 0
      }
      this.axios
        .post(`/spdHERPService/productsInfos/hosKindcode/query`, param)
        .then(res => {
          var ids = []
          for (var i = 0; i < res.data.data.length; i++) {
            ids[i] = res.data.data[i].id
          }
          this.loading = false
          this.expadoIds = ids
        })
    },
    handleNodeClick (data) {
      var _this = this
      _this.selectedNode = data
	  var ids = []
	  _this.getChildrenIds(data, ids)
      // _this.goodFilters.lbsx = data.id
	  _this.goodFilters.ids = ids
      _this.getHosGoodsList(1)
    },
    getChildrenIds: function (node, ids) { // 获取子节点的id
      var _this = this
      ids.push(node.id)
      var children = node.children
      if (children && children.length > 0) {
      	for (var i = 0; i < children.length; i++) {
      		_this.getChildrenIds(children[i], ids)
      	}
      }
    },
    getHosGoodsList: function (pIndex) {
      let hosId = this.user.corpId
      this.goodsPage = pIndex
      this.loading = true
      var param = {
        orderBy: '',
        queryObject: {
          hosId: hosId,
          goodsName: this.goodFilters.goodsName,
          goodsGg: this.goodFilters.goodsGg,
          lbsx: this.goodFilters.lbsx,
          provName: this.goodFilters.provName,
          flag: this.goodFilters.flag,
          lbsxs: this.goodFilters.ids
        },
        page: this.goodsPage,
        rows: this.goodsPageSize
      }
      this.axios.post('/spdHERPService/myGoods/hosGoods/getHosGoodsByHos', param).then(
        res => {
          this.loading = false
          if (res.data.code == '0') {
            this.goodList = res.data.data.data
            this.goodsTotal = res.data.data.total
          } else {
            this.$message.error(res.data.msg)
          }
        },
        err => {
          this.loading = false
        }
      )
    },
    searchGoods () {
      this.getHosGoodsList(1)
    },
    handleGoodsSizeChange (val) {
      this.goodsPageSize = val
      this.getHosGoodsList(this.goodsPage)
    },
    handleGoodsCurrentChange (val) {
      this.goodsPage = val
      this.getHosGoodsList(this.goodsPage)
    },
    connect: function () {
      if (this.selectedNode == null) {
        this.$message.error('请从左侧选择一层分组')

        return
      }
      if (this.selectedNode.children) {
        this.$message.error('所选分类下有子分类,不能添加商品')

        return
      }

      this.popTitle = '将商品与分类<' + this.selectedNode.label + '>关联'
      this.popVisible = true
      this.getGoodsListByPop(1)
    },
    disConnect: function () {
      if (this.selectedNode == null) {
        this.$message.error('请从左侧选择一层分组')
      }
    },
    searchPopGoods () {
      this.getGoodsListByPop(1)
    },
    handleSelectionChange4Main (val) {
      this.multipleSelection4Main = val
    },
    handlePopSelectionChange (val) {
      this.multipleSelection4Pop = val
    },
    getGoodsListByPop: function (pIndex) {
      let hosId = this.user.corpId
      this.goodsPage = pIndex
      this.loading = true
      var param = {
        orderBy: '',
        queryObject: {
          hosId: hosId,
          provGoodsName: this.popFilters.goodsName,
          provGoodsGg: this.popFilters.goodsGg,
          provName: this.popFilters.provName,
          isAll: this.popFilters.isAll
        },
        page: this.popPage,
        rows: this.popPageSize
      }
      this.axios
        .post('/spdHERPService/myGoods/provGoodsHos/getProGoods4HosList', param)
        .then(
          res => {
            this.loading = false
            if (res.data.code == '0') {
              this.popGoodList = res.data.data.data
              this.popTotal = res.data.data.total
              //  this.goodsPage = res.data.data.pageNum;
            } else {
              this.$message.error(res.data.msg)
            }
          },
          err => {
            this.loading = false
          }
        )
    },
    handlePopSizeChange (val) {
      this.popPageSize = val
      this.getGoodsListByPop(this.popPage)
    },
    handlePopCurrentChange (val) {
      this.popPage = val
      this.getGoodsListByPop(this.popPage)
    },
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    popSubmit: function () {
      if (this.multipleSelection4Pop.length <= 0) {
        this.$message.error('至少选择一条数据！')
        return
      }
      // this.axios.post('/spdHERPService/myGoods/provGoodsHos/excuteConnect?lbsx='+this.selectedNode.id, this.multipleSelection4Pop).then(res => {
      this.axios
        .post(
          '/spdHERPService/myGoods/provGoodsHos/excuteConnect/' + this.selectedNode.id,
          this.multipleSelection4Pop
        )
        .then(
          res => {
            this.loading = true
            if (res.data.code == '0') {
              this.loading = false
              this.popVisible = false
            } else {
              this.$message.error(res.data.msg)
            }
            this.$refs.multipleTable.clearSelection()
            this.getHosGoodsList(1)
          },
          err => {
            this.loading = false
          }
        )
    },

    goodEdit (item) {
      this.errors.goodsNameErr = ''
      this.errors.codeErr = ''
      this.errors.goodsGgErr = ''
      this.errors.unitErr = ''
      this.errors.mfrsIdErr = ''
      this.errors.madeErr = ''
      this.errors.packeageErr = ''
      this.errors.hitPriceErr = ''
      this.errors.hisPriceErr = ''
      this.errors.priceErr = ''
      this.errors.purModeErr = ''

      if (item) {
        let good = Object.assign({}, item)

        this.good = good
        this.taxRate = this.good.taxRate ? this.good.taxRate : 0.17
        this.kindCode = this.good.kindCode
        this.kind68code = this.good.kind68code
        this.uniqueCodeStrategy = this.good.uniqueCodeStrategy
        this.modelTitle = '修改商品'
        this.selectedId = item.mfrsId
        this.selectedName = item.mfrsName
        this.selectedHosKind = item.hosKindLevelCode
        this.uniqueCodeStrategy = this.good.uniqueCodeStrategy
          ? this.good.uniqueCodeStrategy
          : '3'
      } else {
        this.good = {}
        this.kind68code = ''
        this.kindCode = ''
        this.modelTitle = '添加商品'
        this.selectedId = ''
        this.selectedName = ''
        this.uniqueCodeStrategy = 3 // 默认值 非唯一码管理
        this.taxRate = '0.17'
      }
      this.editFormVisible = true
      //  this.$refs.editForm.resetFields();
    },
    getSL: function () {
      this.loading = true
      this.axios
        .post('/platformService/sys/dict/getDictValueByDictEname', { dictName: 'SL' })
        .then(
          res => {
            this.loading = false
            if (res.data.code == '0') {
              this.SL = res.data.data
            } else {
              this.$message.error(res.data.msg)
            }
          },
          err => {
            this.loading = false
          }
        )
    },
    getDict68s: function () {
      this.loading = true
      this.axios
        .post('/platformService/sys/dict/getDictValueByDictEname', {
          dictName: '68FL'
        })
        .then(
          res => {
            this.loading = false
            if (res.data.code == '0') {
              this.dict68s = res.data.data
            } else {
              this.$message.error(res.data.msg)
            }
          },
          err => {
            this.loading = false
          }
        )
    },
    getDictUCodeStrategys: function () {
      this.loading = true
      this.axios
        .post('/platformService/sys/dict/getDictValueByDictEname', {
          dictName: 'WYMGLCL'
        })
        .then(
          res => {
            if (res.data.code == '0') {
              this.dictUCodeStrategys = res.data.data
            } else {
              this.$message.error(res.data.msg)
            }
            this.loading = false
          },
          err => {
            this.loading = false
          }
        )
    },
    getHosKindCodes: function () {
      this.loading = true
      this.axios
        .post('/spdHERPService/baseData/hosKindcode/list', { hosId: this.user.corpId })
        .then(
          res => {
            if (res.data.code == '0') {
              this.hosKindsCodes = res.data.data
            } else {
              this.$message.error(res.data.msg)
            }
            this.loading = false
          },
          err => {
            this.loading = false
          }
        )
    },
    descUploadSave () {
      let goods = this.goods;
      this.axios.post('/spdHERPService/myGoods/hosGoods/update',goods ).then(
        res => {
          if (res.data.code == '0') {
            this.$message({ message: '操作成功！', type: 'success' })
            this.descDia = false
            this.getHosGoodsList(this.goodsPage)
          } else {
            this.$message.error('操作失败！')
          }
        },
        err => {
        }
      )
    },
    editSubmit: function () {
      this.good.mfrsId = this.selectedId
      this.good.taxRate = this.taxRate
      this.good.kindCode = this.kindCode
      this.good.kind68code = this.kind68code
      this.good.uniqueCodeStrategy = this.uniqueCodeStrategy

      let _this = this
      let form = this.$refs.editForm
      const h = this.$createElement
      let arr = new Array()
      form.validate(valid => {
        if (valid) {
          this.editLoading = true
          let serviceUrl = this.good.id
            ? '/spdHERPService/myGoods/hosGoods/update'
            : '/spdHERPService/myGoods/hosGoods/insert'
          this.axios.post(serviceUrl, this.good).then(
            res => {
              this.editLoading = false
              if (res.data.code == '0') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.editFormVisible = false
                this.getHosGoodsList(this.goodsPage)
              } else if (res.data.code == '-122') {
                let err = res.data.validateErrors.fieldErrors[0]
                if (err.field == 'code') {
                  this.errors.codeErr += err.message
                  arr.push(h('span', null, err.message))
                }
                if (err.field == 'mfrsId') {
                  this.errors.mfrsIdErr += err.message
                  arr.push(h('span', null, err.message))
                }
                _this.$msgbox({
                  title: '错误',
                  type: 'error',
                  message: h('p', null, arr)
                })
              } else {
                this.$message.error('操作失败！')
              }
            },
            err => {
              this.editLoading = false
            }
          )
        } else {
          let fields = form.fields
          let msg = ''
          fields.forEach(f => {
            if (f.validateState == 'error') {
              msg += '</br>' + f.validateMessage
              if (arr.length > 0) arr.push(h('br', null, null))
              arr.push(h('span', null, f.validateMessage))
            }
          })
          _this.$msgbox({
            title: '错误',
            type: 'error',
            message: h('p', null, arr)
          })
        }
      })
    },
    delGood (item) {
      // doing  解除与分类的关联
      this.$confirm('确认从该分类中移除该商品吗?', '提示', {})
        .then(() => {
          this.axios.post('/spdHERPService/myGoods/hosGoods/disConnect/', item).then(
            res => {
              this.loading = true
              if (res.data.code == '0') {
                this.$message({ message: '操作成功！', type: 'success' })
                this.loading = false
                this.getHosGoodsList(1)
              } else {
                this.$message.error(res.data.msg)
              }
            },
            err => {
              this.loading = false
            }
          )
        })
        .catch(() => {})
    },
    actGood (good) {
      var me = this
      me.$confirm('确认激活这条记录吗?', '提示', {
      }).then(() => {
        me.loading = true
        good.flag = '1'
        me.axios.post('/spdHERPService/myGoods/hosGoods/update', good).then(res => {
          me.loading = false
          if (res.data.code == '0') {
            me.loading = false
            me.$message({ message: '操作成功！', type: 'success' })
          } else {
            me.$message.error(res.data.msg)
          }
          me.getHosGoodsList(this.page)
        }, err => {
          me.loading = false
        })
      }).catch(() => {

      })
    },
    handleGoodsImg (row) {
      this.imgAddMode = true
      this.uploadDlgLoading = true

      for (let i = 0; i < 6; i++) {
        if (this.$refs['imgUpload' + i]) {
          this.$refs['imgUpload' + i].clearFiles()
        }
      }
      this.dlgGoodsImgTitle = row.goodsName + ' 图片信息'
      this.goodsImgs.hosGoodsId = row.id
      this.goodsImgs.origUrls.fill('')
      this.goodsImgs.tUrls.fill('')

      this.axios.get(this.baseURL + 'get/' + encodeURIComponent(row.id)).then(res => {
        if (res.data.code >= 0) {
          if (res.data.data) {
            this.goodsImgs.id = res.data.data.id
            this.goodsImgs.version = res.data.data.version
            for (let i = 1; i <= 6; i++) {
              if (res.data.data['filePath' + i] && res.data.data['filePath' + i] !== '') {
                this.$set(this.goodsImgs.origUrls, i - 1, res.data.data['filePath' + i])
                let slashIndex = res.data.data['filePath' + i].lastIndexOf('/')
                let tUrl = this.picURLPrefix + res.data.data['filePath' + i].substring(0, slashIndex) + '/thumb-' + res.data.data['filePath' + i].substring(slashIndex + 1)
                this.$set(this.goodsImgs.tUrls, i - 1, tUrl)
              }
            }
            this.imgAddMode = false
          }
        } else {
          this.$message.error(res.data.msg)
        }
        this.uploadDlgLoading = false
      }).catch(err => {
        this.$message.error('获取商品图片信息失败，请联系管理员！' + err)
        this.uploadDlgLoading = false
      })
      this.dlgGoodsImgVisible = true
    },
    beforeUpload (file) {
      if (!this.allowedFileTypes.includes(file.type)) {
        this.$message.error('文件格式不符合要求，请按要求上传' + this.allowedFileTypes.join(',') + '文件！')
        return false
      }
      if (file.size / 1024 / 1024 > 15) {
        this.$message.error('上传图片大小不能超过15M！')
        return false
      }
    },
    handleLocalAdd0 (file, fileList) {
      this.handleLocalAdd(file, fileList, 0)
    },
    handleLocalAdd1 (file, fileList) {
      this.handleLocalAdd(file, fileList, 1)
    },
    handleLocalAdd2 (file, fileList) {
      this.handleLocalAdd(file, fileList, 2)
    },
    handleLocalAdd3 (file, fileList) {
      this.handleLocalAdd(file, fileList, 3)
    },
    handleLocalAdd4 (file, fileList) {
      this.handleLocalAdd(file, fileList, 4)
    },
    handleLocalAdd5 (file, fileList) {
      this.handleLocalAdd(file, fileList, 5)
    },
    handleLocalAdd (file, fileList, i) {
      fileList.splice(0)
      fileList.push(file)
      this.goodsImgs.tUrls[i] = file.url
    },
    handleUploadSuccess0 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 0)
    },
    handleUploadSuccess1 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 1)
    },
    handleUploadSuccess2 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 2)
    },
    handleUploadSuccess3 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 3)
    },
    handleUploadSuccess4 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 4)
    },
    handleUploadSuccess5 (response, file, fileList) {
      this.handleUploadSuccess(response, file, fileList, 5)
    },
    handleUploadSuccess (response, file, fileList, i) {
      if (response.code < 0) {
        this.$message({
          type: 'error',
          showClose: 'true',
          duration: 0,
          message: file.name + '上传失败，' + response.msg
        })
      } else {
        this.$message.success(file.name + '上传成功！')
        let { ext, id, path } = response.data[0]
        this.$set(this.goodsImgs.origUrls, i, path + '/' + id + '.' + ext)
      }
    },
    descUploadSuccess (response, file, fileList, i) {
      if (response.code < 0) {
        this.$message({
          type: 'error',
          showClose: 'true',
          duration: 0,
          message: file.name + '上传失败，' + response.msg
        })
      } else {
        let curfile = response.data[0]
        this.$message.success(curfile.name + '上传成功！')
        let { ext, id, path } = response.data[0]
        this.goods.goodsDescFile = path + '/' + id + '.' + ext
        this.goods.fileName = curfile.name
      }
    },
    handleUploadErr (_, file, fileList) {
      this.$message.error('上传图片失败，请联系管理员！')
    },
    handleDlgUploadConfirm () {
      this.uploadBtnLoading = true
      let actionUrl = ''
      if (this.imgAddMode) {
        actionUrl = this.baseURL + 'add/' + encodeURIComponent(this.goodsImgs.hosGoodsId)
      } else {
        actionUrl = this.baseURL + 'mod/' + this.goodsImgs.id + '/' + this.goodsImgs.version
      }
      this.axios.post(actionUrl, this.goodsImgs.origUrls)
        .then(res => {
          if (res.data.code < 0) {
            this.$message.error(res.data.msg)
          } else {
            this.$message.success('修改商品图片信息成功！')
            if (!this.imgAddMode) {
              this.goodsImgs.version = res.data.data
            }
            this.getHosGoodsList(this.goodsPage);
          }
          this.uploadBtnLoading = false
          this.dlgGoodsImgVisible = false;
        }).catch(err => {
          this.uploadBtnLoading = false
          this.$message.error('修改商品图片信息失败，请联系管理员！' + err)
        })
    },
    handleDelHosGoodsImage (id, index) {
      this.$set(this.delBtnLoading, index, true)
      let actionUrl = this.baseURL + 'del/' + this.goodsImgs.id + '/' + this.goodsImgs.version + '/filePath' + (index + 1)
      this.axios.patch(actionUrl).then(res => {
        if (res.data.code < 0) {
          this.$message.error(res.data.msg)
        } else {
          this.$message.success('删除商品图片信息成功！')
          this.goodsImgs.version += 1
          this.$set(this.goodsImgs.origUrls, index, '')
        }
        this.$set(this.delBtnLoading, index, false)
      }).catch(err => {
        this.$message.error('删除商品图片信息失败，请联系管理员！' + err)
        this.$set(this.delBtnLoading, index, false)
      })
    },
    getCGMS: function () {
      this.axios.post('/platformService/sys/dict/getDictValueByDictEname', {dictName: 'CGMS'}).then(res => {
        if (res.data.code == '0') {
          this.purModes = res.data.data
        } else {
          this.$message.error(res.data.msg)
        }
      }, err => {})
    }
  },

  mounted () {
    this.filters.hosId = this.user.corpId
    this.getKinds()
    this.getHosGoodsList(1)
    this.getSL()
    this.getDict68s()
    this.getDictUCodeStrategys()
    // this.getHosKindCodes()
    this.getCGMS()
    this.selectedHosKind = null
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}

.el-button {
  margin: 0;
}

.el-pagination {
  float: right;
}

.splitborder {
  border-right: rgba(0, 70, 255, 0.6) 1px dashed;
}

.el-tree-node__label {
  font-size: 12px;
}
.el-table {
  font-size: 12px;
}
.el-tree {
  min-height: calc(100% - 75px);
}
.goods-img {
  height: 250px;
  text-align:center
}
</style>

<style>
#provGoodsHoslist .pic-uploader .el-upload {
  border: 1px dashed #9b9b9b;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

#provGoodsHoslist .upload-background .el-upload::after {
  content: '';
  display: block;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-size: 100%;
  background-image: url("../../assets/images/logo_200x200.png");
  background-repeat: no-repeat;
  opacity: 0.20;
}

#provGoodsHoslist .pic-uploader .el-upload:hover {
  border-color: #20a0ff;
}

#provGoodsHoslist .pic-uploader .item-status-label {
  position: absolute;
  right: -15px;
  top: -6px;
  width: 40px;
  height: 24px;
  background: #13ce66;
  text-align: center;
  transform: rotate(45deg);
  box-shadow: 0 0 1pc 1px rgba(0, 0, 0, .2);
}

#provGoodsHoslist .pic-uploader .item-status-label .upload-success {
  font-size: 12px;
  margin-top: 11px;
  transform: rotate(-45deg) scale(.8);
  color: #fff;
}

#provGoodsHoslist .pic-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

#provGoodsHoslist .pic {
  min-width: 50px;
  min-height: 50px;
  max-width: 178px;
  max-height: 178px;
  display: block;
}

#provGoodsHoslist .el-upload-list__item,
.el-upload-list__item:first-child {
  margin-top: 0;
  margin-bottom: 5px;
}
</style>

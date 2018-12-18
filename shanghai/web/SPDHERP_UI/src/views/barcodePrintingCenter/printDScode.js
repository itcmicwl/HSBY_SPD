import lodop from '@/common/third/lodop/LodopFuncs';
var callLodop = function (LODOP, codeInfo) {
  LODOP.ADD_PRINT_TEXTA("name",5, 10, 210, 45, codeInfo.goodsName +"(" +codeInfo.goodsGg+")");
  LODOP.SET_PRINT_STYLEA("name", "FontSize", 10);
  LODOP.ADD_PRINT_BARCODE(50, 10, 200, 35, '128A', codeInfo.packageId);
  LODOP.NEWPAGEA();   //分页
}
var printDsCodes = function (codeLst) {
  var LODOP = lodop.getLodop();
  LODOP.PRINT_INIT("dsPrint");
  LODOP.SET_PRINT_PAGESIZE(1,"700","400","barcodePage");
  if (Array.isArray(codeLst) && codeLst.length > 0) {
    codeLst.forEach(item => {
      callLodop(LODOP, item);
    });
  }
  LODOP.PREVIEW();
  //LODOP.PRINT_DESIGN();
}
export default printDsCodes;
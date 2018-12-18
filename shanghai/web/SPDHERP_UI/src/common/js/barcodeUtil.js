import axios from '../../config/axiosConfig';
import { BARCODE_PARSE } from './constance';
let barcodeUtil = {
  parseBarCodeByMainCode: function(barcode, hosId, provId) {
    var param = {
      hosId: hosId,
      provId: provId,
      masterBarcode: barcode
    };
    return axios.post('/spdHERPService/baseData/basBarcode/parseBarcode', param);
  },
  parseCombinedBarcode: function(barcode, slaverBarcode, hosId, provId) {
    var param = {
      hosId: hosId,
      provId: provId,
      masterBarcode: barcode,
      slaverBarcode: slaverBarcode
    };
    return axios.post('/spdHERPService/baseData/baseDataMaint/BarcodeParse/parseBarcodeCombine', param);
  },  
  validBarcodePackage(barcode) {
    return barcode.toLowerCase().startsWith(BARCODE_PARSE.PACKAGE.PREFIX) && barcode.length === BARCODE_PARSE.PACKAGE.LENGTH
  },
  validBarcodeUnique(barcode) {
    return barcode.toLowerCase().startsWith(BARCODE_PARSE.UNIQUE.PREFIX) && barcode.length === BARCODE_PARSE.UNIQUE.LENGTH
  }
}
export default barcodeUtil;

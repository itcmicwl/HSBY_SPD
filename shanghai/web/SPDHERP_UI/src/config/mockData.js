// import Mock from 'mockjs';
// var baseUrl = 'http://localhost:8083'
// var Random = Mock.Random
// Random.natural()
// Random.cword()
// Random.string()
// Random.date()
// Random.datetime()
// Random.float()
// export var sendBill =  Mock.mock('/spdHERPService/yg/ygSend/getSendBillLst', {
//   'code':0,
//   'data': {
//     'detail|1-10':[{
//       'psdbh|+1': 123456789,
//       'psdh|+1':206587535,
//       'qybm':'QX56806983600',
//       'psmxts|2-30':100,
//       'psdzt|1':['00', '01', '10']
//     }],
//     'repHead':{
//       'acceptDatetime':Random.datetime( 'yyyyMMDD/HHmmss/' ),
//       'res':'',
//       'errMsg':'',
//       'remark':''
//     },
//     'main':{
//       'sfwj':'0',
//       'dczhpsbh':'',
//       'jls':'10'
//     }
//   },
//   'msg':'',
//   'tag':'',
//   'validateErrors':null
// });
// export var billView = Mock.mock('/spdHERPService/yg/ygSend/getSendBillByBillId/', {
//   'code':0,
//   'data': {
//     'detail|5-30':[{
//       'psmxbh|+1': 123456789,
//       'ddmxbh|+1':206587535,
//       'sxh|+1':1,
//       'xsddh|+1':367535,
//       'pm':'@cword(5,10)',
//       'gg':'@cword(5,10)',
//       'xh':'@cword(5,10)',
//       'dw|1':['箱', '件', '包'],
//       'scqy':'@cword(5,10)',
//       'psl|+1':367535,
//     }],
//     'repHead':{
//       'acceptDatetime':Random.datetime( 'yyyyMMDD/HHmmss/' ),
//       'res':'',
//       'errMsg':'',
//       'remark':''
//     },
//     'main':{
//       'sfwj':'0',
//       'dczhpsbh':'',
//       'jls':'10'
//     }
//   },
//   'msg':'',
//   'tag':'',
//   'validateErrors':null
// });
// export var invoiceLst =  Mock.mock('/spdHERPService/yg/invoice/getInvoiceBillLst', {
//   'code':0,
//   'data': {
//     'detail|1-10':[{
//       'fpbh|+1': "@natural()",
//       'fpdm|+1':"@natural()",
//       'fph|+1':"@natural()",
//       'fprq':'@date("yyyyMMdd")',
//       'fphszje':'@string("number", 5)',
//       'qybm':'@string("number", 15)',
//       'yybm':'@string("number", 15)',
//       'psdbm':'@string("number", 8)',
//       'cglx|1':['1', '2', '3','9'],
//       'fpbz':'@cword(5,10)',
//       'fpmxts':'@string("number", 2)',
//       'fpzt|1':['00', '01', '10','20','21','30']
//     }],
//     'repHead':{
//       'acceptDatetime':"@datetime( 'yyyyMMDD/HHmmss/' )",
//       'res':'',
//       'errMsg':'',
//       'remark':''
//     },
//     'main':{
//       'sfwj':'0',
//       'dczhfpbh':'',
//       'jls':'10'
//     }
//   },
//   'msg':'',
//   'tag':'',
//   'validateErrors':null
// });
// export var invoiceBill =  Mock.mock('/spdHERPService/yg/invoice/getInvoiceDetailByBillId/', {
//   'code':0,
//   'data': {
//     'detail|5-20':[{
//       'fpmxbh': "@natural()",
//       'sfwpsfp|1':['0', '1'],
//       'wpsfpsm':'@cword(5,10)',
//       'sfch|1':['0', '1'],
//       'hctbdm':'@string("number", 10)',
//       'hcxfdm':'@string("number", 10)',
//       'yybddm':'@string("number", 8)',
//       'ggxhsm':'@cword(5,10)',
//       'glmxbh':'@string("number", 8)',
//       'xsddh':'@string("number", 8)',
//       'scph':'@string("number", 8)',
//       'scrq':'@date("yyyyMMdd")',
//       'yxrq':'@date("yyyyMMdd")',
//       'spsl':'@string("number", 3)',
//       'wsdj':'@float(60, 100, 2,2)',
//       'hsdj':'@float(60, 100, 2,2)',
//       'sl':'@string("number", 2)',
//       'se':'@string("number", 4)',
//       'hsje':'@string("number", 6)',
//       'pfj':'@string("number", 2)',
//       'lsj':'@string("number", 2)',
//       'ZCZH':'@string("number", 8)'      
//     }],
//     'repHead':{
//       'acceptDatetime':"@datetime( 'yyyyMMDD/HHmmss/' )",
//       'res':'',
//       'errMsg':'',
//       'remark':''
//     },
//     'main':{
//       'sfwj':'0',
//       'dczhfpbh':'',
//       'jls':'10'
//     }
//   },
//   'msg':'',
//   'tag':'',
//   'validateErrors':null
// });

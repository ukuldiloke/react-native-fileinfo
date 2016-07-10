const NativeModules = require('NativeModules');

var Fileinfo = {};

Fileinfo.getFileinfo = function(uri) {
  return NativeModules.ReactNativeFileinfo.getFileinfo(uri);
}

module.exports = Fileinfo;

const NativeModules = require('NativeModules');

Fileinfo.getFileInfo = function(uri) {
  const path = uri.replace('file://', '');
  return NativeModules.ReactNativeFileinfo.getFileInfo(path)
    .then(result => {
      return result;
    });
}

module.exports = Fileinfo;

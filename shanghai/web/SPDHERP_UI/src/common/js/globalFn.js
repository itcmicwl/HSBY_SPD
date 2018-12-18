import {appConfig} from '@/config/appConfigs';
window.LocalStorage = {
  getItem: function (key) {
      return localStorage.getItem(appConfig.appCode + key);
  },
  setItem: function (key, data) {
      return localStorage.setItem(appConfig.appCode + key, data);
  }
}
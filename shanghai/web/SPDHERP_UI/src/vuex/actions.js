export const userLogin = ({commit},user) => {
    commit('userLogin',user)
}
export const loginDialogShow = ({commit},flag) => {
    commit('loginDialogShow',flag)
}
export const setSysRoutes = ({commit},data) => {
    commit('setSysRoutes',data)
}
export const setSideNavs = ({commit},data) => {
    commit('setSideNavs',data)
}
export const setHomeNavs = ({commit},data) => {
    commit('setHomeNavs',data)
}
export const setPageEls = ({commit},data) => {
    commit('setPageEls',data)
}
export const setSettleBill = ({commit},data) => {
    commit('setSettleBill',data)
}
export const setSettleBill4Out = ({commit},data) => {
    commit('setSettleBill4Out',data)
}
export const clearCache = ({commit}) => {
    commit('clearCache')
}
export const setSpdMsg =  ({commit},data) => {
    commit('setSpdMsg',data)
}
export const setUserMenuTree =  ({commit},data) => {
    commit('setUserMenuTree',data)
}
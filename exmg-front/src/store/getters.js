const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  currentUser: state => state.user.currentUser,
  currentDept: state => state.user.currentDept,
  currentRole: state => state.user.currentRole,
  deptRoleList: state => state.user.deptRoleList,
  permission_routes: state => state.permission.routes,
  errorLogs: state => state.errorLog.logs
}
export default getters

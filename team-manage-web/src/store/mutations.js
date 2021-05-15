export default {
  // 修改token，并将token存入localStorage
  changeToken(state, token) {
    state.token = token
    localStorage.setItem('token', token)
  },
  storeUserinfo(state, info) {
    state.userId = info.userId
    state.username = info.username
    state.role = info.role
    state.roleName = info.roleName
    localStorage.setItem('userId', info.userId)
    localStorage.setItem('username', info.username)
    localStorage.setItem('role', info.role)
    localStorage.setItem('roleName', info.roleName)
  }
}

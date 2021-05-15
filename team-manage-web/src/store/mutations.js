export default {
  // 修改token，并将token存入localStorage
  changeToken(state, token) {
    state.token = token
    localStorage.setItem('token', token)
  }
}

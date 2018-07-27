import React, { Component } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'
import './App.css';
import axios from 'axios'
import NotFound from './scenes/NotFound/NotFound'
import Login from './scenes/Login/Login'
import Home from './scenes/Home/Home'
import Loading from './components/gereric/Loading/Loading'
import LoginService from './services/LoginService'
import PostForm from './scenes/PostForm/PostForm'
class App extends Component {

  constructor(){
    super()
    this.state = {}
    this.configureRequestInterceptor()
  }

  configureRequestInterceptor() {
    const self = this
    axios.interceptors.request.use((config) => {
      self.showLoader(true)
      return config
    });

    axios.interceptors.response.use((response) => {
      self.showLoader(false)
      return response;
    }, (error) => {
      if (error.response.status === 401) {
        self.setUnauthorized()
      }
      self.showLoader(false)
      return Promise.reject(error)
    })
  }
setUnauthorized(){
  this.setState({
    unAuthorized:true
  })
}
showLoader(showLoader){
  this.setState({
    showLoader
  })
}
onClickLogoutButton() {
  LoginService.logout().then(() => {
    return <Redirect to={"/"} />
  })
}



  render() {
    if(this.state.unAuthorized){
      return <Redirect to="/login"/>
    }
    return (
      <div className="App">
      <Loading showLoader={this.state.showLoader}/>
      <Switch>
      <Route path="/404" exact component={NotFound}/>
        <Route path="/postForm" exact component={PostForm} />
        <Route path="/login" exact component={Login}/>
        <Route path="/home" exact component={Home}/>
        <Route path="/" exact component={Login}/>
        <Redirect to="/404" />
        </Switch>
      </div>

            
    );
  }
}

export default App;

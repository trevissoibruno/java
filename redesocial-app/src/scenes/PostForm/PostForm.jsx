
import React from 'react'
import PostService from '../../services/PostService'
import Button from '../../components/gereric/Button/Button'
import ContentShadow from '../../components/gereric/BoxShadow/BoxShadow'
import { Redirect } from 'react-router-dom';
import NavBar from '../../components/gereric/NavBar/NavBar'
import Form from '../../components/gereric/Form/Form'
import './PostForm.css'
import Alert from '../../components/gereric/Alert/Alert'
const POST_SAVED_MESSAGE = 'Resenha Inserida com Sucesso'

export default class PostForm extends React.Component {

    constructor() {
        super()
        this.state = this.getInitialState()
        this.handleChange = this.handleChange.bind(this)
        this.onPostRegisterButtonClick = this.onPostRegisterButtonClick.bind(this)
    }
    getInitialState() {
        return {
            title: '',
            image: '',
            text: '', 
            redirectLoginPage: false,         
        }
    }
    
    handleChange(event) {
        const target = event.target
        const value = target.value
        const name = target.name
        this.setState({
            [name]: value
        })
    }

    onPostRegisterButtonClick() {
       PostService.create(this.state).then((result) => {
            this.setState(this.getInitialState())
            this.setAlert('success',POST_SAVED_MESSAGE)
        }).catch(resp => {
            this.setAlert('danger', resp.response.data.error)
        })
    }

    setAlert(type, message) {
        this.setState({
            alert: {
                type,
                message
            }
        })
    }
    renderAlert() {
        return this.state.alert ? <Alert classAlert={this.state.alert.type} text={this.state.alert.message} /> : undefined
    }

    render() {
        if (this.state.redirectLoginPage) {
            return <Redirect to="/login" />;
          }
        return (
            <div>
                <NavBar/>
            <div className="PostForm">
                <div className="PostForm--content">
                    <ContentShadow>
                    {this.renderAlert()}
                        <Form 
                           title={this.state.title}
                           handleChange={this.handleChange}
                           image={this.state.image}
                           text={this.state.text}  /> 
                        <div className="pull-right">
                            <Button isOutline={true} classButton="warning" type="button" onClick={this.onPostRegisterButtonClick} text="Registrar" />
                        </div>
                    </ContentShadow>
                </div>
            </div>
            </div>
        )

    }

}
import React from "react";
import RegisterService from '../../services/RegisterService'
import Input from '../../components/gereric/Input/Input'
import Button from '../../components/gereric/Button/Button'
import { Redirect} from 'react-router-dom'
export default class RegisterForm extends React.Component {
  constructor() {
    super();
    this.state = this.getInitialState();
    this.handleChange = this.handleChange.bind(this);
    this.onRegisterButtonClick = this.onRegisterButtonClick.bind(this);
  }

  getInitialState() {
    return {
      email: "",
      senha: "",
      nome: "",
      apelido: "",
      imagem: "",
      dataDeNascimento: "",
      shouldRedirectLogin: false,
    };
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]: value
    });
  }

  goLogin() {
    this.setState({
        shouldRedirectLogin: true
    })
}

  onRegisterButtonClick() {
    RegisterService.register(
      this.state.email,
      this.state.senha,
      this.state.nome,
      this.state.apelido,
      this.state.dataDeNascimento,
      this.state.imagem,
      
    )
      .then(result => {
        this.props.onRegisterSuccess()
       
      })
      .catch(resp => {
        this.setState({
          error: resp.response.data.error
        });
      });
  }

  

  render() {
    if (this.state.shouldRedirectLogin) {
      return <Redirect to='/' />
  }
    return (
      <div className= "Container">
       
        <Input
          placeholder="E-mail"
          name="email"
          type="text"
          handleChange={this.handleChange}
          label="E-mail"
        />
        <Input
          placeholder="Senha"
          name="senha"
          type="password"
          handleChange={this.handleChange}
          label="Senha"
        />
        <Input
          placeholder="Nome"
          name="nome"
          type="text"
          handleChange={this.handleChange}
          label="Nome"
        />
        <Input
          placeholder="Apelido"
          name="apelido"
          type="text"
          handleChange={this.handleChange}
          label="Apelido"
        />
        <Input
          placeholder="Imagem"
          name="imagem"
          type="text"
          handleChange={this.handleChange}
          label="Imagem"
        />
        <Input
          placeholder="Data de Nascimento"
          name="dataDeNascimento"
          type="date"
          handleChange={this.handleChange}
          label="dataDeNascimento"
        />
        <div className="pull-right">
          <Button
            isOutline={true}
            classButton="primary"
            type="button"
            onClick={this.onRegisterButtonClick}
            text="Registrar"
          />
        </div>
      </div>
    );
  }
}

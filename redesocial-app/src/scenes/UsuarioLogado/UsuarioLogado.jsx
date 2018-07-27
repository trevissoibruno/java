import React from "react";
import UsuarioService from '../../services/UsuarioService'
import { Card,  CardImg, CardTitle, CardDeck,
    CardSubtitle, CardBody } from 'reactstrap';
import './UsuarioLogado.css'
export default class UsuarioLogado extends React.Component {

    constructor(){
        super()
        this.state={
            usuario:{}
        }
    }

    componentDidMount() {
        UsuarioService.getUsuarioLogado().then(UsuarioLogado => {
            this.setState({
              usuario: UsuarioLogado.data
            });
          })
          .catch(err => {
            console.error(err);
          });
    }

    render(){
        return(
            <div className="usuario"> 
            <CardDeck>
            <Card>
              <div className="imagem">
              <CardImg top width="100%" src={this.state.usuario.imagem} alt="Sem imagem" />
              </div>
              <CardBody>
                  <br/>
                <CardTitle>{this.state.usuario.nome}</CardTitle>
                <CardSubtitle>{this.state.usuario.apelido}</CardSubtitle>
              </CardBody>
            </Card>
          </CardDeck>  
          </div> 
        );
    }







}
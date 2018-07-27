import React from "react";
import { Redirect } from "react-router-dom";
import {
  Container,
  Row,
  Col,
  CardFooter,
  Modal, ModalHeader, ModalBody, ModalFooter
} from "reactstrap";
import Input from '../../components/gereric/Input/Input'
import Post from '../../scenes/Post/Post'
import PostService from '../../services/PostService'
import NavBar from '../../components/gereric/NavBar/NavBar'
import './Home.css'
import Amigos from '../../scenes/Amigos/Amigos'
import AmigoService from '../../services/AmigoService'
import Button from '../../components/gereric/Button/Button'
import UsuarioLogado from '../UsuarioLogado/UsuarioLogado'
export default class Login extends React.Component {
  constructor() {
    super();
    this.state = {
      redirectLoginPage: false,
      posts: [],
      amigos: [],
      amigosPosts:[],
      pagina : 0,
      comentar: false,
      comentario:'',
      postComentario:{},
    };
    this.incrementaPagina = this.incrementaPagina.bind(this)
    this.decrementaPagina = this.decrementaPagina.bind(this)
    this.renderTodosPosts = this.renderTodosPosts.bind(this)
    this.comentar = this.comentar.bind(this)
    this.enviarComentario = this.enviarComentario.bind(this)
    this.handleChange = this.handleChange.bind(this)
    
  }

  handleChange(event) {
    const target = event.target
    const value = target.value
    const name = target.name
    this.setState({
        [name]: value
    })
}

  comentar(postComentario) {
    this.setState({
      comentar: !this.state.comentar,
      postComentario
    });
  }

  enviarComentario(){
    PostService.enviarComentario(this.state.comentario,this.state.postComentario.id).then(result => {
      this.setState({
        comentar: !this.state.comentar,
      });
    })
    .catch(err => {
      console.error(err);
    });
  }

  componentDidMount() {
    this.renderTodosPosts()
}

renderTodosPosts(){
  PostService.getPosts(this.state.pagina).then(postsList => {
    this.setState({
      posts: postsList.data
    });
  })
  .catch(err => {
    console.error(err);
  });

  PostService.getPostsAmigos().then(postsListAmigos => {
    this.setState({
      amigosPosts: postsListAmigos.data
    });
  })
  .catch(err => {
    console.error(err);
  });

  AmigoService.getAmigos().then(listAmigos => {
    this.setState({
      amigos: listAmigos.data
    });
  })
  .catch(err => {
    console.error(err);
  });
}

incrementaPagina(){
  this.setState({
      pagina : this.state.pagina + 1,
  });
  this.renderTodosPosts()
}

decrementaPagina(){
  this.setState({
    pagina : this.state.pagina - 1
  });
  this.renderTodosPosts()
}



renderComentarios(comentarios){
  return comentarios.map((comentario, key) => {
    return <div key={comentario.id}>
        <CardFooter>
          {comentario.nomeUsuario}
          <br/>
          {comentario.texto}
        </CardFooter>
       </div>
    
  });


}
  renderPosts() {
    return this.state.posts.map((post, key) => {
      return <div key={post.id}>
          <Post
          image={post.imagem}
          title={post.titulo}
          usuario={post.usuarioDto.nome}
          text={post.texto}
          data={post.localDateTime}
          onClick={() => this.comentar(post)}
          comentario={this.renderComentarios(post.comentarios)}
          />
         </div>
      
    });
  }

  renderPostsAmigos() {
    return this.state.amigosPosts.map((amigosPost, key) => {
      return <div key={amigosPost.id}>
          <Post
          image={amigosPost.imagem}
          title={amigosPost.titulo}
          usuario={amigosPost.usuarioDto.nome}
          text={amigosPost.texto}
          data={amigosPost.localDateTime}
          onClick={() => this.comentar(amigosPost)}
          comentario={this.renderComentarios(amigosPost.comentarios)}
          />
          
         </div>
      
    });
  }


  renderAmigos(){
    return this.state.amigos.map((amigo, key) => {
      return <div key={amigo.id}>
          <Amigos
          imagem={amigo.amigo.imagem}
          apelido={amigo.amigo.nome}
          />
         </div>
    });

  }

  render() {
    if (this.state.redirectLoginPage) {
      return <Redirect to="/login" />;
    }
    return (
      <div>
        <NavBar/>
        <div className="fundo">
      <Container>
        <Row>
          <Col sm="2" md={{ size: 0, offset: 1 }}> <UsuarioLogado/></Col>
          <Col sm="6" md={{ size: 6, offset: 0 }}>
          
          {this.renderPosts()}
          {this.renderPostsAmigos()}
          </Col>
          <Col sm="6" md={{ size: 2, offset: 1 }}>
           {this.renderAmigos()}
          </Col>
          <Col></Col> 
          <Row>
        </Row>

        </Row>
        <Row>
        <Col sm="12" md={{ size: 6, offset: 4 }}>
        
        <Button isOutline={false} classButton="warning" type="button" onClick={this.decrementaPagina} text="Voltar" />
        <Button isOutline={false} classButton="warning" type="button" onClick={this.incrementaPagina} text="Avançar" />
        </Col>
        </Row>
      </Container>
      <div>
        
        
        <Modal isOpen={this.state.comentar} fade={false} comentar={this.comentar} className="modal1">
          <ModalHeader comentar={this.comentar}>Comentar</ModalHeader>
          <ModalBody>
          <Input placeholder="Comentario" name="comentario" type="text" handleChange={this.handleChange} />
          
          </ModalBody>
          <ModalFooter>
            <Button isOutline={false} classButton="warning" type="button" onClick={this.enviarComentario} text="Enviar Comentario"/>
            <Button isOutline={false} classButton="danger" type="button" onClick={this.comentar} text="Cancelar"/>
          </ModalFooter>
        </Modal>
      </div>
      </div>
      </div>
    );
  }
}

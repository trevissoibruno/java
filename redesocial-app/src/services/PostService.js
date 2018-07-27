import CONFIG from "../config";
import axios from "axios";
import LoginService from "./LoginService";
export default class PostService {
  static getPosts(pagina) {
    return axios.get(`${CONFIG.API_URL_BASE}/post/buscar?page=${pagina}&size=5`, {
      headers: {
        Authorization: `${LoginService.getLoggedUser()}`,
        "Content-Type": "application/json"
      }
    });
  }

  static getPostsAmigos() {
    return axios.get(`${CONFIG.API_URL_BASE}/post`, {
      headers: {
        Authorization: `${LoginService.getLoggedUser()}`,
        "Content-Type": "application/json"
      }
    });
  }

  static create(post) {
    return axios.post(`${CONFIG.API_URL_BASE}/post/criar`,
        {
            'titulo': post.title,
            'texto': post.text,
            'imagem': post.image
        },
        {
            headers: {
                Authorization: `${LoginService.getLoggedUser()}`,
                'Content-Type': 'application/json',
            }
        })
}
static enviarComentario(texto,post) {
  return axios.post(`${CONFIG.API_URL_BASE}/comentario/${post}`,
      {
          texto
      },
      {
          headers: {
              Authorization: `${LoginService.getLoggedUser()}`,
              'Content-Type': 'application/json',
          }
      })
}


}

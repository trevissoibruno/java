import CONFIG from "../config";
import axios from "axios";
import LoginService from "./LoginService";

export default class AmigoService {
  static getAmigos() {
    return axios.get(`${CONFIG.API_URL_BASE}/amizade/amizades/aceitas`, {
      headers: {
        Authorization: `${LoginService.getLoggedUser()}`,
        "Content-Type": "application/json"
      }
    });
  }
}

import { Injectable } from "@angular/core";
import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { AuthService } from "./AuthService";



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService){}

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        if (req.url.includes('/auth/api')) {
      return next.handle(req);
    }
        const authToken = this.authService.getToken();
        if (authToken) {
            req = req.clone({
                setHeaders: {
                    Authorization: `Bearer ${authToken}`
                }

            
            });
        }
        return next.handle(req);
    }

}
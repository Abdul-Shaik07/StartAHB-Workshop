import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})

export class AuthService {
    private credentialsBaseApiUrl = 'http://localhost:8080/auth/api';
    
    constructor(private http: HttpClient) {}

    register(user: any) {
        return this.http.post(`${this.credentialsBaseApiUrl}/registerUsers`, user);
    }

    login(credentials: any) {
        return this.http.post(`${this.credentialsBaseApiUrl}/login`, credentials);
    }
    
    saveToken(token: string) {
        localStorage.setItem('authToken', token);
    }

    getToken(): string | null {
        return localStorage.getItem('authToken');
    }

    isLoggedIn(): boolean {
        return this.getToken() !== null;
    }

    logout() {
        localStorage.removeItem('authToken');
    }

}

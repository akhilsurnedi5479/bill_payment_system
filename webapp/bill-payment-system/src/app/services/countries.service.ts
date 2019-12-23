import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Country } from '../model/country.model';
@Injectable({
    providedIn:'root'
})
export class CountriesService{
    url:string="assets/countries.json"

    constructor(private httpClient:HttpClient){}
    getCountries():Observable<Country[]>{
        return this.httpClient.get<Country[]>(this.url);
    }
    
    getStates(country:String){
        
    }
}
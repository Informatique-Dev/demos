import { Injectable } from '@angular/core';
import { Configuration, Environment } from '../models/configuration.model';
import { HttpClient } from '@angular/common/http';
import { SettingsService } from './settings.service';
import { mergeMap, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable()

export class ConfigService {
  private config!: Configuration;
  private env: Environment = { env: 'development' };
  configUrl = '/config';
  envUrl = 'env';
  constructor( private httpClient:HttpClient) {
  }


  load():Promise<boolean> {
    return new Promise((resolve, reject) => {
      this.loadConfig().subscribe(
        (config) => {
          this.config = config;
         SettingsService.configurationEnvironment = this.config;
          resolve(true);
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  loadConfig():Observable<Configuration> {
    return this.loadEnvironment().pipe(
      mergeMap((currentEnv) => {
        this.env = currentEnv;
        return this.loadFile<Configuration>(currentEnv.env, this.configUrl);
      })
    );
  }

  loadEnvironment(): Observable<Environment> {
    if (environment.production) this.envUrl = 'env-prod';
    return this.loadFile<Environment>(this.envUrl, this.configUrl);
  }

  loadFile<T>(env: string, url: string): Observable<T> {
    return this.getJSON(`${url}/${env}.json`);
  }

  public getJSON<T>(url: string): Observable<T> {
    return this.httpClient.get<T>(url);
  }

}



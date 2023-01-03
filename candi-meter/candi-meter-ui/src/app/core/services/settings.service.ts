import { Injectable } from '@angular/core';
import { Configuration } from '../models/configuration.model';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  static configurationEnvironment: Configuration = { api: { baseUrl: '' }};
}

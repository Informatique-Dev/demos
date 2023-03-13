import { Investors } from './models/investor';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InvestorsRepository extends ResourceService<Investors> {
  getResourceUrl(): string {
    return 'investor';
  }
}

import { TestBed } from '@angular/core/testing';

import { InstallmentRepositry } from './installment.repositry';

describe('RepositryService', () => {
  let service: InstallmentRepositry;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InstallmentRepositry);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

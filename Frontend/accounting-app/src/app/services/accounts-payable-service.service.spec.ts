import { TestBed } from '@angular/core/testing';

import { AccountsPayableServiceService } from './accounts-payable-service.service';

describe('AccountsPayableServiceService', () => {
  let service: AccountsPayableServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountsPayableServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

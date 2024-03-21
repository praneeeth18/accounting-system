import { TestBed } from '@angular/core/testing';

import { AccountsReceivableServiceService } from './accounts-receivable-service.service';

describe('AccountsReceivableServiceService', () => {
  let service: AccountsReceivableServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountsReceivableServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

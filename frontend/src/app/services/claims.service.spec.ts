import { TestBed } from '@angular/core/testing';

import { ClaimsServiceService } from './claims.service';

describe('ClaimsService', () => {
  let service: ClaimsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClaimsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

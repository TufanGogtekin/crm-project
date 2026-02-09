import { TestBed } from '@angular/core/testing';

import { VecihleService } from './vecihle-service';

describe('VecihleService', () => {
  let service: VecihleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VecihleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

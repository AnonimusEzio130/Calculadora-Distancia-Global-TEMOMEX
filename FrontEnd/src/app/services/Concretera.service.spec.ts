import { TestBed } from '@angular/core/testing';

import { ConcreteraService } from './Concretera.service';

describe('ConcreteraService', () => {
  let service: ConcreteraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConcreteraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

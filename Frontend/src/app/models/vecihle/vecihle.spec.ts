import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Vecihle } from './vecihle';

describe('Vecihle', () => {
  let component: Vecihle;
  let fixture: ComponentFixture<Vecihle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Vecihle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Vecihle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

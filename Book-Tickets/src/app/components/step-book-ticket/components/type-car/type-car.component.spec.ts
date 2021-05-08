import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeCarComponent } from './type-car.component';

describe('TypeCarComponent', () => {
  let component: TypeCarComponent;
  let fixture: ComponentFixture<TypeCarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeCarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

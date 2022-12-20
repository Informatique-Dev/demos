import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstallmentComponent } from './installment.component';

describe('InstallmentComponent', () => {
  let component: InstallmentComponent;
  let fixture: ComponentFixture<InstallmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstallmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstallmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInstallmentComponent } from './edit-installment.component';

describe('EditInstallmentComponent', () => {
  let component: EditInstallmentComponent;
  let fixture: ComponentFixture<EditInstallmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditInstallmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInstallmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

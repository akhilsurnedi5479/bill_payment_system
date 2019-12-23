import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentGatewayDebitComponent } from './payment-gateway-debit.component';

describe('PaymentGatewayDebitComponent', () => {
  let component: PaymentGatewayDebitComponent;
  let fixture: ComponentFixture<PaymentGatewayDebitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentGatewayDebitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentGatewayDebitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

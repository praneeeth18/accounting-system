import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HpFooterComponent } from './hp-footer.component';

describe('HpFooterComponent', () => {
  let component: HpFooterComponent;
  let fixture: ComponentFixture<HpFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HpFooterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HpFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

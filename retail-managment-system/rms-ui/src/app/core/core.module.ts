import { NgModule, Optional, SkipSelf } from '@angular/core';

import { RouterModule } from '@angular/router';
@NgModule({
  declarations: [],
  imports: [
    RouterModule
  ],
  exports: []
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only'
      );
    }
  }
}
